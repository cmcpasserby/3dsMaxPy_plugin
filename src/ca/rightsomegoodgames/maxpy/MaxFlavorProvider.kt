package ca.rightsomegoodgames.maxpy

import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.VirtualFile
import com.jetbrains.python.sdk.flavors.PythonFlavorProvider
import com.jetbrains.python.sdk.flavors.PythonSdkFlavor
import icons.PythonIcons
import java.io.File
import javax.swing.Icon

class MaxSDKFlavor private constructor() : PythonSdkFlavor() {
    override fun isValidSdkHome(path: String?): Boolean {
        val file = File(path)
        return file.isFile && isValidSdkPath(file)
    }

    override fun isValidSdkPath(file: File): Boolean {
        val name = FileUtil.getNameWithoutExtension(file).toLowerCase()
        return name.startsWith("3dsmaxpy")
    }

    override fun getVersionOption(): String {
        return "--version"
    }

    override fun getName(): String {
        return "3dsMaxPy"
    }

    override fun getIcon(): Icon {
        return PythonIcons.Python.Python
    }

    override fun getSdkPath(path: VirtualFile): VirtualFile? {
        return path
    }

    companion object {
        var INSTANCE = MaxSDKFlavor()
    }
}

class MaxFlavorProvider : PythonFlavorProvider {
    override fun getFlavor(platformIndependent: Boolean) = MaxSDKFlavor.INSTANCE
}
