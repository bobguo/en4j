/*
 *  Copyright (C) 2010 Ruben Laguna <ruben.laguna@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.rubenlaguna.en4j.NoteContentViewModule;

import com.rubenlaguna.en4j.interfaces.NoteRepository;
import com.rubenlaguna.en4j.noteinterface.Note;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.openide.util.Exceptions;
import org.openide.util.LookupEvent;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.util.Lookup;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xhtmlrenderer.render.Box;
import org.xhtmlrenderer.simple.XHTMLPanel;
import org.xhtmlrenderer.simple.extend.XhtmlNamespaceHandler;
import org.xhtmlrenderer.swing.BasicPanel;
import org.xhtmlrenderer.swing.FSMouseListener;
import org.xhtmlrenderer.swing.MouseTracker;
import org.xhtmlrenderer.swing.SwingReplacedElementFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.rubenlaguna.en4j.NoteContentViewModule//NoteContentView//EN",
autostore = false)
public final class NoteContentViewTopComponent extends TopComponent implements LookupListener {

    private static final Logger LOG = Logger.getLogger(NoteContentViewTopComponent.class.getName());
    private static NoteContentViewTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "NoteContentViewTopComponent";
    private Lookup.Result<Note> result = null;
    private XHTMLPanel panel = null;
    private final ENMLReplacedElementFactory cef;

    public NoteContentViewTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(NoteContentViewTopComponent.class, "CTL_NoteContentViewTopComponent"));
        setToolTipText(NbBundle.getMessage(NoteContentViewTopComponent.class, "HINT_NoteContentViewTopComponent"));
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        cef = new ENMLReplacedElementFactory(new SwingReplacedElementFactory());
        panel = new XHTMLPanel();
        //panel.getListeners(MouseListener.class);
        MouseListener[] mls = (MouseListener[]) (panel.getListeners(MouseListener.class));
        for (MouseListener mouseListener : mls) {
            LOG.info("removing " + mouseListener);
            if (mouseListener instanceof MouseTracker) {
                final MouseTracker mouseTracker = (MouseTracker) mouseListener;
                List<FSMouseListener> fsmlList = mouseTracker.getListeners();
                for (FSMouseListener fsml : fsmlList) {
                    LOG.info("removing FSMouseListener: " + fsml);
                    mouseTracker.removeListener(fsml);
                }
            }
            panel.removeMouseListener(mouseListener);
        }
        panel.addMouseTrackingListener(new FSMouseListener() {

            public void onMouseOver(BasicPanel pnl, Box box) {
                LOG.fine("onMouseOver");
            }

            public void onMouseOut(BasicPanel pnl, Box box) {
                LOG.fine("onMouseOut");
            }

            public void onMouseUp(BasicPanel pnl, Box box) {
                LOG.fine("onMouseUp");
                if (box == null || box.getElement() == null) {
                    return;
                }

                String uri = findLink(panel, box.getElement());
                LOG.fine("onMouseUp: uri: " + uri);

                if (uri != null) {
                    try {
                        Desktop.getDesktop().browse(new URI(uri));
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (URISyntaxException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }

            private String findLink(BasicPanel panel, Element e) {
                String uri = null;

                for (Node node = e; node.getNodeType() == Node.ELEMENT_NODE; node = node.getParentNode()) {
                    uri = panel.getSharedContext().getNamespaceHandler().getLinkUri((Element) node);

                    if (uri != null) {
                        break;
                    }
                }

                return uri;
            }

            public void onMousePressed(BasicPanel pnl, MouseEvent me) {
                LOG.fine("onMousePressed");
            }

            public void onMouseDragged(BasicPanel pnl, MouseEvent me) {
                LOG.fine("onMouseDragged");
            }

            public void reset() {
                LOG.fine("reset");
            }
        });

        panel.getSharedContext().setReplacedElementFactory(cef);
        jScrollPane2.setViewportView(panel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized NoteContentViewTopComponent getDefault() {
        if (instance == null) {
            instance = new NoteContentViewTopComponent();
        }
        return instance;
    }

    public void resultChanged(LookupEvent ev) {
        try {
            Collection<? extends Note> notes = result.allInstances();

            if (!notes.isEmpty()) {
                //get the id only because the contents are lazy loaded and
                //the entity is already detached.
                int id = notes.iterator().next().getId();

                //get(id) will gives us a fully loaded entity
                Note n = Lookup.getDefault().lookup(NoteRepository.class).get(id);
                cef.setNote(n);

                // Create a builder factory
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setValidating(false);
                //factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
                factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
                factory.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);

                // Create the builder and parse the file
                Reader content = n.getContentAsReader();
                if (null != content) {
                    try {
                        Document doc = factory.newDocumentBuilder().parse(new InputSource(content));
                        panel.setDocument(doc, "", new ENMLNamespaceHandler(new XhtmlNamespaceHandler()));
                    } catch (NullPointerException ex) {
                        LOG.warning("NPE when trying to process: " + content);
                        Exceptions.printStackTrace(ex);
                    }
                } else {
                    LOG.warning("empty contents for note " + n.getGuid());
                }
            }

        } catch (SAXException e) {
            // A parsing error occurred; the xml input is not valid
            Logger.getLogger(getName()).log(Level.SEVERE, "Exception", e);
        } catch (ParserConfigurationException e) {
            Logger.getLogger(getName()).log(Level.SEVERE, "Exception", e);
        } catch (IOException e) {
            Logger.getLogger(getName()).log(Level.SEVERE, "Exception", e);
        }

    }

    // Parses an XML file and returns a DOM document.
    // If validating is true, the contents is validated against the DTD
    // specified in the file.
    public static Document parseXmlFile(String filename, boolean validating) {
        try {
            // Create a builder factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(validating);

            // Create the builder and parse the file
            Document doc = factory.newDocumentBuilder().parse(new File(filename));


            return doc;
        } catch (SAXException e) {
            // A parsing error occurred; the xml input is not valid
        } catch (ParserConfigurationException e) {
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * Obtain the NoteContentViewTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized NoteContentViewTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(NoteContentViewTopComponent.class.getName()).warning("Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }

        if (win instanceof NoteContentViewTopComponent) {
            return (NoteContentViewTopComponent) win;
        }
        LOG.warning("There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
        Lookup.Template<Note> tpl = new Lookup.Template<Note>(Note.class);
        result = Utilities.actionsGlobalContext().lookup(tpl);
        result.addLookupListener(this);
        resultChanged(null);
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
    }

    Object readProperties(java.util.Properties p) {
        NoteContentViewTopComponent singleton = NoteContentViewTopComponent.getDefault();
        singleton.readPropertiesImpl(p);

        return singleton;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
}
