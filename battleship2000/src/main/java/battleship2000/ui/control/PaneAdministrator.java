package battleship2000.ui.control;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * A user interface control class for adding necessary elements to a pane.
 *
 * @author Petri Kallio
 */
public class PaneAdministrator {
    private Container container;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    
    /**
     * A constructor to create a new instantiation of the class.
     * <p>
     * The adding of components to the {@link java.awt.GridBagLayout} is handled
     * via an instantiation of the {@link java.awt.GridBagConstraints} class.
     * 
     * @see                 java.awt.GridBagConstraints
     * @param container     the {@link java.awt.Container} to administrate
     * @param layout        the {@link java.awt.GridBagLayout} for the container
     */
    public PaneAdministrator(Container container, GridBagLayout layout) {
        this.container = container;
        this.layout = layout;
        this.gbc = new GridBagConstraints();
        this.container.setLayout(this.layout);
    }
    
    /**
     * Adds a {@link java.awt.Component} to the container.
     * 
     * @param component     the component to be added
     * @param gridx         x-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param gridy         y-coordinate of the component in the {@link java.awt.GridBagLayout}
     */
    public void addComponent(Component component, int gridx, int gridy) {
        addComponent(component, gridx, gridy, 666, 666, 666, 666, new Insets(0, 0, 0, 0));
    }
    
    /**
     * Adds a {@link java.awt.Component} to the container.
     * 
     * @param component     the component to be added
     * @param gridx         x-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param gridy         y-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param anchor        the anchoring point of the component in the {@link java.awt.GridBagLayout}
     */
    public void addComponent(Component component, int gridx, int gridy, int anchor) {
        addComponent(component, gridx, gridy, anchor, 666, 666, 666, new Insets(0, 0, 0, 0));
    }
    
    /**
     * Adds a {@link java.awt.Component} to the container.
     * 
     * @param component     the component to be added
     * @param gridx         x-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param gridy         y-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param anchor        the anchoring point of the component in the {@link java.awt.GridBagLayout}
     * @param fill          the fill value of the component in the {@link java.awt.GridBagLayout}
     */
    public void addComponent(Component component, int gridx, int gridy, int anchor, int fill) {
        addComponent(component, gridx, gridy, anchor, fill, 666, 666, new Insets(0, 0, 0, 0));
    }
    
    /**
     * Adds a {@link java.awt.Component} to the container.
     * 
     * @param component     the component to be added
     * @param gridx         x-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param gridy         y-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param insets        the {@link java.awt.Insets} of the component
     */
    public void addComponent(Component component, int gridx, int gridy, Insets insets) {
        addComponent(component, gridx, gridy, 666, 666, 666, 666, insets);
    }
    
    /**
     * Adds a {@link java.awt.Component} to the container.
     * 
     * @param component     the component to be added
     * @param gridx         x-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param gridy         y-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param anchor        the anchoring point of the component in the {@link java.awt.GridBagLayout}
     * @param insets        the {@link java.awt.Insets} of the component
     */
    public void addComponent(Component component, int gridx, int gridy, int anchor, Insets insets) {
        addComponent(component, gridx, gridy, anchor, 666, 666, 666, insets);
    }
    
    /**
     * Adds a {@link java.awt.Component} to the container.
     * 
     * @param component     the component to be added
     * @param gridx         x-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param gridy         y-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param anchor        the anchoring point of the component in the {@link java.awt.GridBagLayout}
     * @param fill          the fill value of the component in the {@link java.awt.GridBagLayout}
     * @param gridwidth     the amount of columns for the component to span in the {@link java.awt.GridBagLayout}
     */
    public void addComponent(Component component, int gridx, int gridy, int anchor, int fill, int gridwidth) {
        addComponent(component, gridx, gridy, anchor, fill, gridwidth, 666, new Insets(0, 0, 0, 0));
    }
    
    /**
     * Adds a {@link java.awt.Component} to the container.
     * 
     * @param component     the component to be added
     * @param gridx         x-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param gridy         y-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param anchor        the anchoring point of the component in the {@link java.awt.GridBagLayout}
     * @param fill          the fill value of the component in the {@link java.awt.GridBagLayout}
     * @param gridwidth     the amount of columns for the component to span in the {@link java.awt.GridBagLayout}
     * @param insets        the {@link java.awt.Insets} of the component
     */
    public void addComponent(Component component, int gridx, int gridy, int anchor, int fill, int gridwidth, Insets insets) {
        addComponent(component, gridx, gridy, anchor, fill, gridwidth, 666, insets);
    }
    
    /**
     * Adds a {@link java.awt.Component} to the container.
     * 
     * @param component     the component to be added
     * @param gridx         x-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param gridy         y-coordinate of the component in the {@link java.awt.GridBagLayout}
     * @param anchor        the anchoring point of the component in the {@link java.awt.GridBagLayout}
     * @param fill          the fill value of the component in the {@link java.awt.GridBagLayout}
     * @param gridwidth     the amount of columns for the component to span in the {@link java.awt.GridBagLayout}
     * @param gridheight    the amount of rows for the component to span in the {@link java.awt.GridBagLayout}
     * @param insets        the {@link java.awt.Insets} of the component
     */
    public void addComponent(Component component, int gridx, int gridy, 
            int anchor, int fill, int gridwidth, int gridheight, Insets insets) {
        gbc.gridx = checkValue(gbc.gridx, gridx);
        gbc.gridy = checkValue(gbc.gridy, gridy);
        gbc.fill = checkValue(gbc.fill, fill);
        gbc.anchor = checkValue(gbc.anchor, anchor);
        gbc.gridwidth = checkValue(gbc.gridwidth, gridwidth);
        gbc.gridheight = checkValue(gbc.gridheight, gridheight);
        gbc.insets = insets;
        
        layout.setConstraints(component, gbc);
        container.add(component);
    }

    private int checkValue(int former, int future) {
        if (future != 666) {
            return future;
        } else {
            return former;
        }
    }
}