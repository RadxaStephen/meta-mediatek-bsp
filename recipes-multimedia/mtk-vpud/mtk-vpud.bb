# Copyright (C) 2020 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek VPUD daemon"
LICENSE = "CLOSED"
RDEPENDS:${PN} = " libgcc glibc "

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit systemd
inherit update-rc.d

SRC_URI = "${AIOT_RITY_URI}/vpud.git;protocol=ssh;branch=main"
SRCREV = "63c1d0f007987cca3bf0e714ce15834de88b90af"

SRC_URI += " \
	file://vpud.service \
	file://vpud.sh \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_buildclean[noexec] = "1"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = ' \
	BINDIR=${D}${bindir} \
	LIBDIR=${D}${libdir} \
'

EXTRA_OEMAKE:append:aarch64 += ' \
	VPUD_ARCH=aarch64 \
'

EXTRA_OEMAKE:append:armv7a += ' \
	VPUD_ARCH=aarch32 \
'

EXTRA_OEMAKE:append:mt8183 += ' \
	VPUD_SOC=mt8183 \
'

EXTRA_OEMAKE:append:mt8365 += ' \
	VPUD_SOC=mt8365 \
'

do_install() {
	oe_runmake install

	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}" = "systemd" ]; then
		install -d ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/vpud.service ${D}${systemd_unitdir}/system/
	else
		install -d ${D}${sysconfdir}/init.d
		install -m 0755 ${WORKDIR}/vpud.sh ${D}${sysconfdir}/init.d/vpud
	fi
}

SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${PN}', '', d)}"
SYSTEMD_SERVICE:${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'vpud.service', '', d)}"

INITSCRIPT_NAME = "vpud"

FILES:${PN} += " \
	${bindir}/vpud \
	${libdir}/libvpud_vcodec.so \
	${sysconfdir}/init.d/vpud \
	${sysconfdir}/systemd/system/multi-user.target.wants/vpud.service \
	${systemd_unitdir}/system/vpud.service \
"

FILES:${PN}:append:armv7a = " \
	${libdir}/libvcodec_oal.so \
	${libdir}/libherope_sa.ca7.so \
"

INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "file-rdeps"
INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN}-dev += "file-rdeps"
INSANE_SKIP:${PN}-dev += "dev-elf"
INSANE_SKIP:${PN}-dev += "ldflags"
EXCLUDE_FROM_SHLIBS = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
