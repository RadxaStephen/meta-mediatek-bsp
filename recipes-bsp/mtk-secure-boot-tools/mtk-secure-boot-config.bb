require recipes-bsp/mtk-secure-boot-tools/mtk-secure-boot-config.inc

do_populate_sysroot() {
	install -d ${SYSROOT_DESTDIR}${sysconfdir}/secure
	install ${WORKDIR}/key.ini ${SYSROOT_DESTDIR}${sysconfdir}/secure/key.ini
	install ${WORKDIR}/pl_gfh_config_pss.ini ${SYSROOT_DESTDIR}${sysconfdir}/secure
	if [ "${@oe.utils.conditional('DA_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
		install ${DA_KEY} ${SYSROOT_DESTDIR}${sysconfdir}/secure
	fi
}

inherit deploy

do_deploy() {
    if [ "${@oe.utils.conditional('DA_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
        install -d ${DEPLOYDIR}/
        install ${WORKDIR}/secure/auth_sv5.auth ${DEPLOYDIR}/
    fi
}

addtask do_deploy after do_install