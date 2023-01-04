# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)
#
# To use this recipe:
#  1. Update the "LINUX_VERSION" macro and the upstream commit hash ID in "SRC_REV".
#  2. Set the "PACKAGE_VERSION_linux-mtk" in "local.conf" to match the "LINUX_VERSION".

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI:remove = "${AIOT_BSP_URI}/linux.git;protocol=ssh;branch=${SRCBRANCH}"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=https;branch=${SRCBRANCH}"

LINUX_VERSION ?= "6.0.0"
SRCBRANCH ?= "master"

# 6.2-rc1
SRCREV ?= "1b929c02afd37871d5afb9d498426f83432e71c2"
KERNEL_VERSION_SANITY_SKIP="1"
