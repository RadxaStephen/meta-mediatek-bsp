# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Imagination Technologies Binaries"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=a4f7801866fbc4b8713aadccdf4da58d"

DEPENDS += "wayland"

PROVIDES = " \
	virtual/egl \
	virtual/libegl \
	virtual/libgbm \
	virtual/libgl \
	virtual/libgles2 \
	virtual/mesa \
"

RPROVIDES_${PN} = " \
	egl \
	libegl \
	libgbm \
	libgl \
	libgles2 \
	mesa \
	mesa-vulkan-drivers \
"

S = "${WORKDIR}/git"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/imgtec-pvr.git;protocol=ssh"
SRCREV = "682bfde7078d4346e05fd6fbebfc515bd04f68f3"

EXTRA_OEMAKE = ' \
	IMGTEC_VERSION=${IMGTEC_VERSION} \
	IMGTEC_FW=${IMGTEC_FW} \
	IMGTEC_SH=${IMGTEC_SH} \
'

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
	oe_runmake install EXEC_PREFIX=${D}${exec_prefix} LIBDIR=${D}${libdir} \
			NONARCH_BASE_LIBDIR=${D}${nonarch_base_libdir} \
			SYSCONFDIR=${D}${sysconfdir} BINDIR=${D}/${bindir}
	rm -f ${D}/usr/lib/libvulkan.so*
}

PACKAGES =+ "${PN}-tests"

FILES_${PN} = "${libdir}/*.so* \
               ${libdir}/dri/*.so \
               ${nonarch_base_libdir}/firmware/rgx.fw.22.40.54.30 \
               ${nonarch_base_libdir}/firmware/rgx.sh.22.40.54.30 \
               ${exec_prefix}/local/lib/dri/*.so \
               ${datadir}/mesa/* \
               ${datadir}/drirc.d/* \
               ${sysconfdir}/*"

FILES_${PN}-tests = "${bindir}/*"

FILES_${PN}-dev = "${libdir}/pkgconfig/*.pc \
                   ${datadir}/pkgconfig/*.pc \
                   ${includedir}/EGL/* \
                   ${includedir}/KHR/* \
                   ${includedir}/GLES/* \
                   ${includedir}/GLES2/* \
                   ${includedir}/GLES3/* \
                   ${includedir}/CL/* \
                   ${includedir}/CL_HPP/* \
                   ${includedir}/gbm.h \
                   ${includedir}/GL"

INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} += "already-stripped"
