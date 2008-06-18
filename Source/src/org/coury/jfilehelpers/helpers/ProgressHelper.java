/*
 * ProgressHelper.java
 *
 * Copyright (C) 2007 Felipe Gonçalves Coury <felipe.coury@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package org.coury.jfilehelpers.helpers;

import org.coury.jfilehelpers.enums.ProgressMode;
import org.coury.jfilehelpers.progress.ProgressChangeHandler;
import org.coury.jfilehelpers.progress.ProgressEventArgs;

/**
 * @author Robert Eccardt
 * 
 */
public class ProgressHelper {
	public static void notify(ProgressChangeHandler handler, ProgressMode mode,	int current, int total) {
		if (handler == null)
			return;

		if (mode == ProgressMode.DontNotify)
			return;

		switch (mode) {
		case NotifyBytes:
			handler.handleProgressChange(new ProgressEventArgs(mode, current, total));
			break;

		case NotifyRecords:
			handler.handleProgressChange(new ProgressEventArgs(mode, current, total));
			break;

		case NotifyPercent:
			if (total == -1)
				return;
			handler.handleProgressChange(new ProgressEventArgs(mode, (int) (current * 100 / total), 100));
			break;
		}
	}
}
