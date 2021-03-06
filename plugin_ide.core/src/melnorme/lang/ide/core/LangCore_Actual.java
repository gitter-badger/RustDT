/*******************************************************************************
 * Copyright (c) 2015 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.lang.ide.core;

import com.github.rustdt.ide.core.cargomodel.RustBundleModelManager;
import com.github.rustdt.ide.core.cargomodel.RustBundleModelManager.RustBundleModel;
import com.github.rustdt.ide.core.engine.RustSourceModelManager;
import com.github.rustdt.ide.core.operations.RustBuildManager;
import com.github.rustdt.ide.core.operations.RustToolManager;

import melnorme.lang.ide.core.engine.SourceModelManager;
import melnorme.lang.ide.core.operations.AbstractToolManager;
import melnorme.lang.ide.core.project_model.LangBundleModel;

public class LangCore_Actual {
	
	public static final String PLUGIN_ID = "com.github.rustdt.ide.core";
	public static final String NATURE_ID = PLUGIN_ID +".nature";
	
	public static final String BUILDER_ID = PLUGIN_ID + ".Builder";
	public static final String BUILD_PROBLEM_ID = PLUGIN_ID + ".build_problem";
	public static final String SOURCE_PROBLEM_ID = PLUGIN_ID + ".source_problem";
	
	// Note: the variable should not be named with a prefix of LANGUAGE, 
	// or it will interfere with MelnormeEclipse templating
	public static final String NAME_OF_LANGUAGE = "Rust";
	
	
	public static LangCore instance;
	
	/* ----------------- Owned singletons: ----------------- */
	
	protected final AbstractToolManager toolManager;
	protected final RustBundleModelManager bundleManager;
	protected final RustBuildManager buildManager;
	protected final RustSourceModelManager sourceModelManager;
	
	public LangCore_Actual() {
		instance = (LangCore) this;
		
		toolManager = createToolManagerSingleton();
		bundleManager = createBundleModelManager();
		buildManager = createBuildManager(bundleManager.getModel());
		sourceModelManager = createSourceModelManager();
	}
	
	public static AbstractToolManager createToolManagerSingleton() {
		return new RustToolManager();
	}
	
	public static RustSourceModelManager createSourceModelManager() {
		return new RustSourceModelManager();
	}
	
	public static RustBundleModelManager createBundleModelManager() {
		return new RustBundleModelManager();
	}
	public static RustBuildManager createBuildManager(LangBundleModel bundleModel) {
		return new RustBuildManager(bundleModel);
	}
	
	
	/* -----------------  ----------------- */
	
	
	public static AbstractToolManager getToolManager() {
		return instance.toolManager;
	}
	public static RustBundleModel getBundleModel() {
		return instance.bundleManager.getModel();
	}
	public static RustBuildManager getBuildManager() {
		return instance.buildManager;
	}
	public static RustBundleModelManager getBundleModelManager() {
		return instance.bundleManager;
	}
	public static SourceModelManager getSourceModelManager() {
		return instance.sourceModelManager;
	}
	
}