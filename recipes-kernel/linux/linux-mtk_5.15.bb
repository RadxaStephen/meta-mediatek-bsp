# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.15.37"
SRCBRANCH ?= "mtk-v5.15-dev"
SRCREV = "f71ef722f5e51ec1a041f4cb3fb2285bdb0b51cd"

SRC_URI:append:mt8195 = " \
	file://0001-GENIO-media-i2c-modify-imx214-to-support-extra-exten.patch \
	file://0002-HACK-GENIO-media-i2c-Replace-mbus_code-to-mtk_mbus_c.patch \
"
