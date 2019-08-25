
package com.jfixby.imc.rps.assets.packer;

import java.io.IOException;

import com.jfixby.r3.assets.packer.go.SystemAssetsBankBuilder;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.file.LocalFile;

public class PackAudio {

	public static void pack (final LocalFile rawAssetsToPack, final File tankFolder) throws IOException {
		final File scenes = rawAssetsToPack.child("audio");
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
			packAudio(prefix, F, tankFolder);
		}
	}

	private static void packAudio (final String prefix, final File rawPackageData, final File tankFolder) throws IOException {
		SystemAssetsBankBuilder.packSound(rawPackageData, tankFolder);
	}

}
