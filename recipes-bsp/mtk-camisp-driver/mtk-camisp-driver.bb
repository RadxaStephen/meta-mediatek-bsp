DESCRIPTION = "Mediatek CAMISP-MT8395 Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

COMPATIBLE_MACHINE = "mt8395"

inherit module

SRCREV = "b341d9650889f0f12ca33108553a56c92fa43206"

BRANCH = "master"

SRC_URI += "${AIOT_BSP_URI}/mtk-camisp-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-mtk-camisp-driver"

do_install:append() {
    install -d ${D}${nonarch_base_libdir}/firmware/
    dd if=/dev/zero of=${D}${nonarch_base_libdir}/firmware/remoteproc_scp bs=1 count=100
}

FILES:${PN} += " \
    ${nonarch_base_libdir}/firmware/remoteproc_scp \
"
