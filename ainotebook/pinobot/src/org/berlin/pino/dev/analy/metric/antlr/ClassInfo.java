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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class ClassInfo {
  
  private final Map<String, FieldInfo> fields = new TreeMap<String, FieldInfo>();
  private final String name;
  private final boolean isInterface;
  private final ClassInfo superClass;
  private final List<ClassInfo> interfaces;
  private final String fileName;

  public ClassInfo(String name, boolean isInterface, ClassInfo superClass,
                   List<ClassInfo> interfaces, String fileName) {
      
    this.isInterface = isInterface;
    this.superClass = superClass;
    this.interfaces = interfaces;
    this.fileName = fileName;
    this.name = name.replace("/", ".");
  }

  public String getName() {
    return name;
  }

  public ClassInfo getSuperClass() {
    return superClass;
  }

  public boolean isInterface() {
    return isInterface;
  }

  @Override
  public String toString() {
    return name;
  }

  public FieldInfo getField(String fieldName) {
      
    ClassInfo clazz = this;
    while (clazz != null) {
      FieldInfo fieldInfo = clazz.fields.get(fieldName);
      if (fieldInfo != null) {
        return fieldInfo;
      }
      clazz = clazz.superClass;
    }
    throw new RuntimeException();
  }

  public void addField(FieldInfo fieldInfo) {
    fields.put(fieldInfo.getName(), fieldInfo);
  }

  public Collection getMethods() {
    return null;
  }

  public Collection<FieldInfo> getFields() {
    return fields.values();
  }

  public List<ClassInfo> getInterfaces() {
    return interfaces;
  }

  public Collection getSetters() {
      return null;
  }

  public Collection getNonPrivateConstructors() {
    return null;
  }

  public String getFileName() {
    return fileName;
  }

  public ClassInfo copy() {
    ClassInfo clazz = new ClassInfo(name, isInterface, superClass, interfaces, fileName);    
    return clazz;
  }
}
