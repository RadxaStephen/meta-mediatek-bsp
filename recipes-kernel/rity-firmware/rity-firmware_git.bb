# Copyright (C) 2020 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Additional firmware"
LICENSE = "CLOSED"

inherit allarch

S = "${WORKDIR}/git"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/rity-firmware.git;protocol=ssh"
SRCREV = "fc944ccfe613d9a613ab442c0d3d1eadc287cea2"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
	oe_runmake install NONARCH_BASE_LIBDIR=${D}${nonarch_base_libdir}
}

PACKAGES =+ "${PN}-ap1302-ar0330"
FILES_${PN}-ap1302-ar0330 = "${nonarch_base_libdir}/firmware/ap1302_ar0330_none_fw.bin"