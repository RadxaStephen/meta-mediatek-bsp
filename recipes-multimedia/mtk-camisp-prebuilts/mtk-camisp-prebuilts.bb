DESCRIPTION = "Prebuilt libraries of Mediatek Camera ISP 7.0"
LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

inherit features_check
inherit systemd
inherit update-rc.d

REQUIRED_DISTRO_FEATURES = "nda-mtk"
COMPATIBLE_MACHINE = "mt8395"

SRCREV = "ef53a15ef6cb8afa614d714693ecd59b61298550"
BRANCH = "${DISTRO_CODENAME}"

SRC_URI += "${AIOT_NDA_URI}/mtk-camisp-prebuilts.git;protocol=ssh;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"

do_install() {
	oe_runmake install PWD=${S}/${SOC_FAMILY} LIBDIR=${D}${libdir} INCLUDEDIR=${D}${includedir} DATADIR=${D}${datadir} BINDIR=${D}${bindir}
	chown -R root:root ${D}${libdir}/
	chown -R root:root ${D}${bindir}/
	chown -R root:root ${D}${datadir}/

	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}" = "systemd" ]; then
		install -d ${D}${systemd_unitdir}/system
		install -m 644 ${S}/${SOC_FAMILY}/lib/systemd/system/camd.service ${D}${systemd_unitdir}/system/
	fi
}

SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${PN}', '', d)}"
SYSTEMD_SERVICE:${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'camd.service', '', d)}"

INITSCRIPT_NAME = "camd"

FILES:${PN} += " ${bindir} ${datadir} ${libdir}/*.so "
FILES:${PN} += " ${systemd_unitdir}/system/camd.service "
FILES_SOLIBSDEV = ""

RDEPENDS:${PN} = " \
    gflags \
    glog \
    jpeg \
"

INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "file-rdeps"
INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN}-dev += "file-rdeps"
INSANE_SKIP:${PN}-dev += "dev-elf"
INSANE_SKIP:${PN}-dev += "ldflags"

INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "already-stripped dev-so"
