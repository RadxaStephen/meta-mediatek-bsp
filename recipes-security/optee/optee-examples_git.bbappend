COMPATIBLE_MACHINE = "mt*"

PV = "3.10.0+git${SRCPV}"
SRCREV = "c6eb00a9d83bf068a37e4806c97f582bb16e2973"

EXTRA_OEMAKE_append = " \
    LIBGCC_LOCATE_CFLAGS=--sysroot=${STAGING_DIR_HOST} \
"