i500 Evaluation Board
=====================

The i500 EVB is using the :ref:`platforms/i500:i500 (MT8385)` platform.

Building
--------

You can build an image for the i500 Evaluation Board by setting the
`MACHINE` variable in your `local.conf` to `i500-evb`.

.. code::

	MACHINE=i500-evb

For full build instructions, please refer to the top level `README.md`.

Displays
--------

The i500 Evaluation board supports three differents displays:

* Sharp NT35532 (Red display)
* TPV OTM1901A (Green display)
* Truly R63350A (Yellow display)

The default display when building with `MACHINE=i500-evb` is `Sharp NT35532`.
If your display on your evaluation board is different, you can add support to
your display through device-tree overlays. In order to do so, the related
overlay are located in `recipes-kernel/dtbo/mt8183-evb/`:

* Green display: `panel-tpv-otm1901a.dts`
* Yellow display: `panel-truly-r63350a.dts`

MT7668 wireless chipset
------------------------

The i500 EVB is integrating
a MT7668 wireless chipset.
By default the chipset comes with no MAC or BD address. Please refer to the
MT7668 wireless chipset documentation to see how to
program a MAC or BD address.

Device-Tree Overlays (DTBO)
---------------------------

The following Device-Tree Overlays are supported:

* `panel-tpv-otm1901a.dtbo`: Green display (see :ref:`boards/i500-evb:Displays` section)
* `panel-truly-r63350a.dtbo`: Yellow display (see :ref:`boards/i500-evb:Displays` section)

Limitations
-----------

The software image has the following limitations:

* CSI (Camera), HW accelarated video encode / decode are not yet supported
