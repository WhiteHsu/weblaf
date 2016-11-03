/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.painter.decoration;

import com.alee.api.Identifiable;
import com.alee.api.Mergeable;
import com.alee.api.Overwriting;
import com.alee.managers.style.PainterShapeProvider;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

/**
 * Base interface for component state decorations.
 *
 * @param <E> component type
 * @param <I> decoration type
 * @author Mikle Garin
 */

public interface IDecoration<E extends JComponent, I extends IDecoration<E, I>>
        extends Serializable, Cloneable, Mergeable<I>, Overwriting, Identifiable, PainterShapeProvider<E>
{
    /**
     * States separator.
     */
    public static final String STATES_SEPARATOR = ",";

    /**
     * Called upon decoration activation.
     *
     * @param c painted component
     */
    public void activate ( E c );

    /**
     * Called upon decoration deactivation.
     *
     * @param c painted component
     */
    public void deactivate ( E c );

    /**
     * Returns component states this decoration is describing.
     *
     * @return component states this decoration is describing
     */
    public List<String> getStates ();

    /**
     * Updates states this decoration is describing.
     *
     * @param states states this decoration is describing
     */
    public void updateStates ( List<String> states );

    /**
     * Returns whether this decoration is associated with specified state.
     *
     * @param state decoration state
     * @return true if this decoration is associated with specified state, false otherwise
     */
    public boolean usesState ( String state );

    /**
     * Returns whether this decoration is applicable to the specified states.
     *
     * @param states decoration states
     * @return true if this decoration is applicable to the specified states, false otherwise
     */
    public boolean isApplicableTo ( List<String> states );

    /**
     * Returns whether or not this decoration state provides any visible decoration parts.
     * This method doesn't count content in, so even if content exists decoration might not be visible.
     *
     * @return true if this decoration state provides any visible decoration parts, false otherwise
     */
    public boolean isVisible ();

    /**
     * Returns whether or not this decoration is applied only to a section of the component.
     *
     * @return true if this decoration is applied only to a section of the component, false otherwise
     */
    public boolean isSection ();

    /**
     * Sets whether or not this decoration is applied only to a section of the component.
     *
     * @param section whether or not this decoration is applied only to a section of the component
     */
    public void setSection ( boolean section );

    /**
     * Returns decoration borders size.
     *
     * @param c painted component
     * @return decoration borders size
     */
    public Insets getBorderInsets ( E c );

    /**
     * Returns whether or not decoration has its own content.
     * todo This method is only useful until all content painting is moved inside the decorations
     *
     * @return true if decoration has its own content, false otherwise
     */
    public boolean hasContent ();

    /**
     * Returns decoration baseline for the specified width and height, it is measured from the top of the component.
     * This method is primarily meant for {@code java.awt.LayoutManager}s to align components along their baseline.
     * A return value less than 0 indicates this component does not have a reasonable baseline and that {@code java.awt.LayoutManager}s
     * should not align this component on its baseline.
     *
     * @param c      aligned component
     * @param width  the width to get the baseline for
     * @param height the height to get the baseline for
     * @return decoration baseline for the specified width and height
     */
    public int getBaseline ( E c, int width, int height );

    /**
     * Paints component decoration.
     *
     * @param g2d    graphics context
     * @param bounds painting bounds
     * @param c      painted component
     */
    public void paint ( Graphics2D g2d, Rectangle bounds, E c );

    /**
     * Returns decoration preferred size.
     * It is taken into account when decoration preferred size is being calculated.
     *
     * @param c painted component
     * @return decoration preferred size
     */
    public Dimension getPreferredSize ( E c );
}