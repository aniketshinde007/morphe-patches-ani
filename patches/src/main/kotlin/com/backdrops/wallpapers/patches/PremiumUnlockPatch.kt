package com.backdrops.wallpapers.patches

import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions

val premiumUnlockPatch = bytecodePatch(
    name = "Unlock Premium",
    description = "Forces the premium check to always return true."
) {
    execute {
        val clsClassDef = classDefBy("Lcom/backdrops/wallpapers/data/DatabaseObserver;")
        val mutableCls = mutableClassDefBy(clsClassDef)
        val method = mutableCls.methods.first { it.name == "isPremiumPackUnlocked" }
        
        // Clear existing Dalvik instructions to replace them
        method.implementation?.instructions?.clear()
        
        // Add our force true override
        method.addInstructions(
            0,
            """
                sget-object v0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;
                return-object v0
            """
        )
    }
}
