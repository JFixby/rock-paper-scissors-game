
package com.jfixby.imc.rps.assets.packer;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.file.LocalFile;
import com.jfixby.scarabei.api.java.gc.GCFisher;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.tool.psd2scene2d.PSDRepackSettings;
import com.jfixby.tool.psd2scene2d.PSDRepacker;
import com.jfixby.tool.psd2scene2d.PSDRepackerResult;
import com.jfixby.tool.psd2scene2d.PSDRepackingStatus;

public class PackMainScene {

	public static void pack (final LocalFile rawAssetsToPack, final File tankFolder) throws IOException {
		final File scenes = rawAssetsToPack.child("scenes");
		final FilesList folders = scenes.listDirectChildren(file -> {
			try {
				return file.isFolder();
			} catch (final IOException e) {
				e.printStackTrace();
				return false;
			}
		});

		for (final File F : folders) {
			final String prefix = F.getName() + ".";
			packScenes(prefix, F, tankFolder);
		}
	}

	private static boolean deleteGarbage = false;

	private static void packScenes (final String prefix, final File f, final File tankFolder) throws IOException {
		final File output_folder = tankFolder;
		final FilesList psdFiles = f.listDirectChildren(t -> t.extensionIs("psd"));
		for (final File psd_file : psdFiles) {
			L.d("------------------------------------------------------------------------------------------");

			final String package_name_string = prefix + psd_file.getName();

			final ID package_name = Names.newID(package_name_string);

			final int max_texture_size = (1024);
			final int margin = 0;
			final int texturePadding = 4;
			final int atlasPageSize = 2048 * 2;

			final boolean forceRasterDecomposition = !true;
			final int gemserkPadding = 0;
			L.d("     psd_file", psd_file);
			L.d("output_folder", output_folder);
			L.d(" package_name", package_name_string);
			L.d("max_texture_size", max_texture_size);

			final PSDRepackingStatus status = new PSDRepackingStatus();
			try {

				GCFisher.getMemoryStatistics().print("memory stats");

				final boolean ignore_atlas = !true;

				final PSDRepackSettings settings = PSDRepacker.newSettings();

				settings.setPSDFile(psd_file);
				settings.setPackageName(package_name);
				settings.setOutputFolder(output_folder);
				settings.setMaxTextureSize(max_texture_size);
				settings.setMargin(margin);
				settings.setIgonreAtlasFlag(ignore_atlas);
				settings.setGemserkPadding(gemserkPadding);
				settings.setAtlasMaxPageSize(atlasPageSize);
				settings.setPadding(texturePadding);
				settings.setForceRasterDecomposition(forceRasterDecomposition);
				settings.setUseIndexCompression(!true);
				settings.setUseInMemoryFileSystem(true);
// settings.usePNGQuant(!true);

				final PSDRepackerResult repackingResult = PSDRepacker.repackPSD(settings, status);

			} catch (final Throwable e) {
				e.printStackTrace();
				if (deleteGarbage) {
					final Collection<File> related_folders = status.getRelatedFolders();
					for (final File file : related_folders) {
						file.delete();
						L.d("DELETE", file);
					}
				}
				Sys.exit();

			}

			L.d(" done", package_name_string);

		}
	}

}
