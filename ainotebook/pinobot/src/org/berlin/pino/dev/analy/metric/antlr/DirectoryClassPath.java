/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.berlin.pino.dev.analy.metric.antlr;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DirectoryClassPath implements ClassPath {

	public static class DirectoryFilter implements FileFilter {
		public boolean accept(File dir) {
			return dir.isDirectory() && !dir.getName().equals(".svn");
		}
	}

	public static class FileFileFilter implements FileFilter {
		public boolean accept(File file) {
			return file.isFile();
		}
	}

	private final File rootDirectory;

	public DirectoryClassPath(File rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public boolean isResource(String resource) {
		return !resource.endsWith("/") && getFile(resource).isFile();
	}

	public boolean isPackage(String packageName) {
		return getFile(packageName).isDirectory();
	}

	private File getFile(String path) {
		return new File(rootDirectory, path);
	}

	public String[] listPackages(String packageName) {
		return listNames(packageName, new DirectoryFilter());
	}

	private String[] listNames(String packageName, FileFilter filter) {
		File packageFile = getFile(packageName);
		File[] directories = packageFile.listFiles(filter);
		if (directories == null) {
		  directories = new File[0];
		}
		String[] names = new String[directories.length];
		for (int i = 0; i < directories.length; i++) {
			names[i] = directories[i].getName();
		}
		return names;
	}

	public String[] listResources(String packageName) {
		return listNames(packageName, new FileFileFilter());
	}

	public InputStream getResourceAsStream(String resource) {
		if (isResource(resource))
      try {
        return new FileInputStream(getFile(resource));
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    else
			return null;
	}

  public String[] findResources(String rootPackageName,
      ResourceFilter resourceFilter) {
    return new ResourceFinder(this).findResources(rootPackageName, resourceFilter);
  }

}
