package org.berlin.seesaw.swing.layout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 */
public interface ITeeterLayout {

    /**
     * Method defaultSettings.
     */
    public void defaultSettings();
    
    /**
     * Move the position right.
     * @return GridBagConstraints
     */
    public GridBagConstraints shiftRight();
    
    /**
     * Method shiftDown.
     * @return GridBagConstraints
     */
    public GridBagConstraints shiftDown();
    
    /**
     * @param layout the layout to set
     */
    public void setLayout(final GridBagLayout layout);

    /**
     * @return the layout
     */
    public GridBagLayout getLayout();

    /**
     * @return the constraints
     */
    public GridBagConstraints getConstraints();

    /**
     * @param constraints the constraints to set
     */
    public void setConstraints(final GridBagConstraints constraints);
    
} // End of the interface //
