# Run Userinit

## Scope

Very simple Android app to allow running *userinit* scripts when the system completes booting. All it does is executing `run-parts` on the `/data/local/userinit.d/` directory when the system boot has been completed. Tested on CM12.1.

## Setup

* Install *Run Userinit* using the package manager.
* Run the app once and hit the *Run Userinit* button. Android should now ask you whether you want to allow *Run Userinit* to gain superuser privileges. Confirm and activate the option to remember this confirmation permanently.
* Now *Run Userinit* should execute our *userinit* scripts upon the next reboot.

## Author

* **Alexander Koenig** <alex@lisas.de>

## License

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 3 of the License, or
(at your option) any later version.

For details see *COPYING*.