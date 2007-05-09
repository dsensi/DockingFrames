/**
 * Bibliothek - DockingFrames
 * Library built on Java/Swing, allows the user to "drag and drop"
 * panels containing any Swing-Component the developer likes to add.
 * 
 * Copyright (C) 2007 Benjamin Sigg
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * Benjamin Sigg
 * benjamin_sigg@gmx.ch
 * CH - Switzerland
 */

package bibliothek.gui.dock.security;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;

import bibliothek.gui.dock.station.ScreenDockStation;
import bibliothek.gui.dock.station.screen.ScreenDockDialog;

/**
 * A {@link ScreenDockStation} that uses {@link SecureScreenDockDialog} 
 * instead of {@link ScreenDockDialog}.
 * @author Benjamin Sigg
 *
 */
public class SecureScreenDockStation extends ScreenDockStation {
    /**
     * Creates a new station.
     * @param owner the owner of all dialogs created by this station
     */
    public SecureScreenDockStation( Window owner ) {
        super(owner);
    }

    @Override
    public String getFactoryID() {
        return SecureScreenDockStationFactory.ID;
    }
    
    @Override
    public SecureScreenDockDialog createDialog() {
        Window window = getOwner();
        if( window instanceof Dialog )
            return new SecureScreenDockDialog( this, (Dialog)window );
        else if( window instanceof Frame )
            return new SecureScreenDockDialog( this, (Frame)window );
        else
            throw new IllegalStateException( "Window is not a frame or a dialog" );
    }
}