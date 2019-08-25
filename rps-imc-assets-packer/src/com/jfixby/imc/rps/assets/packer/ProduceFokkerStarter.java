
package com.jfixby.imc.rps.assets.packer;

import java.io.IOException;

import com.jfixby.imc.rps.desktop.RunRPSDesktop;
import com.jfixby.r3.fokker.adaptor.cfg.FokkerStarterConfig;
import com.jfixby.r3.rana.api.pkg.PackerSpecs;
import com.jfixby.r3.rana.red.pkg.bank.PackageUtils;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;
import com.jfixby.scarabei.red.filesystem.virtual.InMemoryFileSystem;

public class ProduceFokkerStarter {

	public static void packFokkerStarter (final File tankFolder) throws IOException {
		final File pkg = tankFolder.child(RunRPSDesktop.FOKKER_STARTER_ASSET_ID);
		pkg.makeFolder();
		final String id_string = pkg.getName();

		final FokkerStarterConfig fokkerConfig = new FokkerStarterConfig();

		fokkerConfig.params.put(FokkerStarterConfig.useGL30, true + "");
		fokkerConfig.params.put(FokkerStarterConfig.width, "1024");
		fokkerConfig.params.put(FokkerStarterConfig.height, "800");
		fokkerConfig.params.put(FokkerStarterConfig.fullscreen, true + "");
		fokkerConfig.params.put(FokkerStarterConfig.TITLE, "Rock Paper Scissors");

		final PackerSpecs specs = new PackerSpecs();

		final InMemoryFileSystem tmp = new InMemoryFileSystem();

		specs.packageFolder = (pkg);
		specs.rootFileName = FokkerStarterConfig.FILE_NAME;

		final File tmpFolder = tmp.ROOT();
		final File tmpFile = tmpFolder.child(specs.rootFileName);
		tmpFile.writeString(Json.serializeToString(fokkerConfig).toString());

		final FilesList files = tmpFolder.listDirectChildren();
		specs.packedFiles.addAll(files);

		final List<ID> packed = Collections.newList();
		final ID id_i = Names.newID(id_string);
		packed.add(id_i);
		specs.packedAssets.addAll(packed);

		specs.packageFormat = (FokkerStarterConfig.PACKAGE_FORMAT);
		specs.version = ("1.0");
		L.d("packing", pkg);
		PackageUtils.pack(specs);
	}

}
