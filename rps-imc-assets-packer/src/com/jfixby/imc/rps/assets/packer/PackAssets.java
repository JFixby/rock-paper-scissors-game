
package com.jfixby.imc.rps.assets.packer;

import java.io.IOException;

import com.github.wrebecca.bleed.RebeccaTextureBleeder;
import com.jfixby.psd.unpacker.api.PSDUnpacker;
import com.jfixby.psd.unpacker.core.RedPSDUnpacker;
import com.jfixby.r3.assets.packer.go.SystemAssetsBankBuilder;
import com.jfixby.r3.fokker.api.FOKKER_SYSTEM_ASSETS;
import com.jfixby.r3.fokker.texture.api.FokkerTexturePackageReader;
import com.jfixby.r3.rana.api.pkg.BankDeploymentSpecs;
import com.jfixby.r3.rana.api.pkg.FileSystemBankSettings;
import com.jfixby.r3.rana.api.pkg.PackagesManager;
import com.jfixby.r3.rana.api.pkg.TankDeploymentSpecs;
import com.jfixby.r3.rana.api.pkg.io.PackageDescriptor;
import com.jfixby.r3.rana.red.pkg.bank.RedPackageManager;
import com.jfixby.r3.rana.red.pkg.bank.RedResourcesManagerSpecs;
import com.jfixby.scarabei.api.desktop.ImageAWT;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFile;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.scarabei.red.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.red.desktop.image.RedImageAWT;
import com.jfixby.texture.slicer.api.TextureSlicer;
import com.jfixby.texture.slicer.red.RedTextureSlicer;
import com.jfixby.tools.bleed.api.TextureBleed;
import com.jfixby.tools.gdx.texturepacker.GdxTexturePacker;
import com.jfixby.tools.gdx.texturepacker.api.TexturePacker;

public class PackAssets {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		PSDUnpacker.installComponent(new RedPSDUnpacker());
		TexturePacker.installComponent(new GdxTexturePacker());
		TextureSlicer.installComponent(new RedTextureSlicer());
// TextureBleed.installComponent(new MaskTextureBleeder());
		ImageAWT.installComponent(new RedImageAWT());
		TextureBleed.installComponent(new RebeccaTextureBleeder());

		final File appFolder = LocalFileSystem.ApplicationHome().parent().child("rps-imc-desktop");

		final File assets_cache_folder = appFolder.child("assets-cache");
		final File assets_folder = appFolder.child("assets");

		{

			assets_folder.makeFolder();
// LocalFileSystem.setDeleteSwitch(LocalFileSystem.DELETE_SWITCH_SAFE_POSITION());
			assets_folder.clearFolder();
		}

		{
			final LocalFile rawAssetsToPack = LocalFileSystem.newFile("D:\\[DEV]\\[GIT-3]\\FokkerTools\\fokker-assets-packer");
			SystemAssetsBankBuilder.deployR3Bank(rawAssetsToPack, assets_folder);
		}

		{
			final RedResourcesManagerSpecs resman_spec = new RedResourcesManagerSpecs();
			resman_spec.assets_cache_folder = assets_cache_folder;
			PackagesManager.installComponent(new RedPackageManager(resman_spec));
		}

		{
			final BankDeploymentSpecs bankDepSpecs = PackagesManager.newBankDeploymentSpecs();
			bankDepSpecs.bankParentFolder = assets_folder;
			bankDepSpecs.bankName = "com.jfixby.imc.rps.assets.local";

			final String tankName = "tank-game";
			{
				final TankDeploymentSpecs tank = new TankDeploymentSpecs();
				tank.tankName = tankName;
				bankDepSpecs.tanks.add(tank);
			}

			final FileSystemBankSettings bank = PackagesManager.deployBank(bankDepSpecs);

			L.d("bank", bank);
			{
				final File bankFolder = bank.bankFolder;
				final File tankFolder = bankFolder.child(tankName);
				ProduceFokkerStarter.packFokkerStarter(tankFolder);
			}

			final LocalFile rawAssetsToPack = LocalFileSystem.newFile("D:\\[DEV]\\[GIT-3]\\rps-imc\\rps-imc-assets");

			{
				final File bankFolder = bank.bankFolder;
				final File tankFolder = bankFolder.child(tankName);

				PackAudio.pack(rawAssetsToPack, tankFolder);
			}

			{
				final File bankFolder = bank.bankFolder;
				final File tankFolder = bankFolder.child(tankName);

				PackMainScene.pack(rawAssetsToPack, tankFolder);
			}

// packSystemAssets(bank.bankFolder, "tank-sys");

// bank.printAllIndexes();
		}

	}

	private static void packSystemAssets (final File bankFolder, final String tankName) throws IOException {
		final File tankFolder = bankFolder.child(tankName);

		packSystemAsset(FOKKER_SYSTEM_ASSETS.RASTER_IS_MISING, tankFolder, FokkerTexturePackageReader.PACKAGE_FORMAT_TEXTURE);
		packSystemAsset(FOKKER_SYSTEM_ASSETS.RASTER_BLACK, tankFolder, FokkerTexturePackageReader.PACKAGE_FORMAT_TEXTURE);
		packSystemAsset(FOKKER_SYSTEM_ASSETS.RASTER_DEBUG_BLACK, tankFolder, FokkerTexturePackageReader.PACKAGE_FORMAT_TEXTURE);
		packSystemAsset(FOKKER_SYSTEM_ASSETS.RASTER_LOGO, tankFolder, FokkerTexturePackageReader.PACKAGE_FORMAT_TEXTURE);

	}

	private static void packSystemAsset (final ID id, final File tank, final String format) throws IOException {

		final PackageDescriptor descriptor = new PackageDescriptor();
		descriptor.format = format;
		descriptor.timestamp = "" + Sys.SystemTime().currentTimeMillis();
		descriptor.version = "1.0";
		descriptor.packed_assets.add(id.toString());
		descriptor.root_file_name = id + ".png";

		final String debug_info = Json.serializeToString(descriptor).toString();
		L.d(debug_info);

		final File package_folder = tank.child(id.toString());

		final File debug = package_folder.child(PackageDescriptor.PACKAGE_DESCRIPTOR_FILE_NAME + ".json");
		debug.writeString(debug_info);
		final File file_pit = package_folder.child(PackageDescriptor.PACKAGE_DESCRIPTOR_FILE_NAME);
		L.d("writing", file_pit);
		file_pit.writeBytes(IO.serialize(descriptor));

		final File content = package_folder.child("content");
		content.makeFolder();
	}

}
