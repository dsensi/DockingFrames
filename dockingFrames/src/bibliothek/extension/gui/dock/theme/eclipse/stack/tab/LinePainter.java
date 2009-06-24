/*
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
package bibliothek.extension.gui.dock.theme.eclipse.stack.tab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import bibliothek.extension.gui.dock.theme.eclipse.stack.EclipseTab;
import bibliothek.extension.gui.dock.theme.eclipse.stack.EclipseTabPane;
import bibliothek.gui.DockController;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.util.color.AbstractDockColor;
import bibliothek.gui.dock.util.color.ColorCodes;
import bibliothek.gui.dock.util.color.ColorManager;
import bibliothek.gui.dock.util.color.DockColor;

/**
 * Paints the background of the tab by just painting a single line.
 * @author Benjamin Sigg
 */
@ColorCodes( "stack.border" )
public class LinePainter implements TabPanePainter {
    private AbstractDockColor color = new AbstractDockColor( "stack.border", DockColor.KIND_DOCK_COLOR, Color.BLACK ){
        @Override
        protected void changed( Color oldColor, Color newColor ) {
            pane.repaint();
        }        
    };
    
    private EclipseTabPane pane;
    
    /**
     * Creates a new painter.
     * @param tabbedComponent the component for which this painter will work
     */
    public LinePainter( EclipseTabPane pane ){
        this.pane = pane;
    }
    
    public void setController( DockController controller ) {
        ColorManager colors = controller == null ? null : controller.getColors();
        color.setManager( colors );
    }
    
    public void paint( Graphics g ){
	    Dockable selection = pane.getSelectedDockable();
	    if( selection == null )
	    	return;
	    
	    EclipseTab tab = pane.getTab( selection );
	    if( tab == null || !tab.isPaneVisible() ) 
	    	return;
	    
	    Rectangle bounds = tab.getBounds();
	    Rectangle available = pane.getAvailableArea();
	    
	    int y = bounds.y + bounds.height-1;
	    
	    g.setColor( color.value() );
	    
	    if( available.x < bounds.x-1 ){
	    	g.drawLine( available.x, y, bounds.x-1, y );
	    }
	    
	    if( available.x + available.width > bounds.x + bounds.width ){
	    	g.drawLine( available.x + available.width, y, bounds.x + bounds.width, y );
	    }
    }
}