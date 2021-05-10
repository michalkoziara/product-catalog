package util

import data.Computer

class Validator {
    fun validateComputers(computers: List<Computer>): Boolean {
        for (computer: Computer in computers) {
            if (!(validateManufacturerName(computer)
                        && validateDiagonalScreenSize(computer)
                        && validateScreenResolution(computer)
                        && validateTouchScreenFlag(computer)
                        && validateNumberOfCpuCores(computer)
                        && validateClockFrequency(computer)
                        && validateRam(computer)
                        && validateDiscSize(computer)
                        && validateGpuMemory(computer))) {
                return false
            }
        }

        return true
    }

    fun validateManufacturerName(computer: Computer): Boolean {
        return computer.manufacturerName.isNotBlank()
    }

    fun validateDiagonalScreenSize(computer: Computer): Boolean {
        return computer.diagonalScreenSize.isEmpty()
                || computer.diagonalScreenSize.matches(Regex("^[1-9][0-9]*\"$"))
    }

    fun validateScreenResolution(computer: Computer): Boolean {
        return computer.screenResolution.isEmpty()
                || computer.screenResolution.matches(Regex("^[1-9][0-9]*x[1-9][0-9]*$"))
    }

    fun validateTouchScreenFlag(computer: Computer): Boolean {
        return computer.touchscreenFlag.isBlank()
                || computer.touchscreenFlag == Constants.YES
                || computer.touchscreenFlag == Constants.NO
    }

    fun validateNumberOfCpuCores(computer: Computer): Boolean {
        return computer.numberOfCpuCores.isEmpty()
                || computer.numberOfCpuCores.matches(Regex("^[1-9][0-9]*$"))
    }

    fun validateClockFrequency(computer: Computer): Boolean {
        return computer.clockFrequency.isEmpty()
                || computer.clockFrequency.matches(Regex("^[1-9][0-9]*$"))
    }

    fun validateRam(computer: Computer): Boolean {
        return computer.ram.isEmpty()
                || computer.ram.matches(Regex("^[1-9][0-9]*GB$"))
    }

    fun validateDiscSize(computer: Computer): Boolean {
        return computer.discSize.isEmpty()
                || computer.discSize.matches(Regex("^[1-9][0-9]*GB$"))
    }

    fun validateGpuMemory(computer: Computer): Boolean {
        return computer.gpuMemory.isEmpty()
                || computer.gpuMemory.matches(Regex("^[1-9][0-9]*GB$"))
    }
}