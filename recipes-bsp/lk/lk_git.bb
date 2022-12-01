S = "${WORKDIR}/git"
BUILD ?= "build-${LK_PROJECT}"

DEPENDS = "libdram-lk"

DEPENDS:append:mt8365 = " libbase-lk-prebuilt"
DEPENDS:append:mt8188 = " libbase-lk-prebuilt"
DEPENDS:append:mt8195 = " libbase-lk-prebuilt"

LICENSE = "MIT & BSD-2-Clause & LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba5e70cccfd7c167a0ace6a1eb1d5457 \
                    file://lib/fdt/LICENSE;md5=abf7cd4d9c12ad07158318352d560fdb \
                    file://LICENSE.aiot;md5=6fcd7dfec853b4eca3b44877b51c0943 "

SRC_URI = "${AIOT_NDA_URI}/lk.git;protocol=ssh;branch=main"
SRCREV = "38ee40031060845321d9ba3d74267e9087789260"

EXTRA_OEMAKE += "LIBDRAM=${STAGING_DIR_TARGET}/${libdir}/libdram-lk.a"
EXTRA_OEMAKE += "LIBBASE=${STAGING_DIR_TARGET}/${libdir}/libbase-lk.a"

do_compile () {
	oe_runmake ARCH_arm64_TOOLCHAIN_PREFIX=${TARGET_PREFIX}			\
			   CFLAGS=""						\
			   DEBUG=0						\
			   SECURE_BOOT_ENABLE=no				\
			   LIBGCC=""						\
			   GLOBAL_CFLAGS="-mstrict-align -mno-outline-atomics"	\
			   ${LK_PROJECT}
}

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"

require lk.inc
