/**
 * This file is part of logisim-evolution.
 *
 * Logisim-evolution is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Logisim-evolution is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along 
 * with logisim-evolution. If not, see <http://www.gnu.org/licenses/>.
 *
 * Original code by Carl Burch (http://www.cburch.com), 2011.
 * Subsequent modifications by:
 *   + College of the Holy Cross
 *     http://www.holycross.edu
 *   + Haute École Spécialisée Bernoise/Berner Fachhochschule
 *     http://www.bfh.ch
 *   + Haute École du paysage, d'ingénierie et d'architecture de Genève
 *     http://hepia.hesge.ch/
 *   + Haute École d'Ingénierie et de Gestion du Canton de Vaud
 *     http://www.heig-vd.ch/
 */

package com.cburch.logisim.gui.prefs;

import static com.cburch.logisim.gui.Strings.S;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.bric.colorpicker.ColorPickerDialog;
import com.cburch.logisim.Main;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.gui.icons.AbstractIcon;
import com.cburch.logisim.prefs.AppPreferences;
import com.cburch.logisim.prefs.PrefMonitor;
import com.cburch.logisim.proj.Project;
import com.cburch.logisim.proj.Projects;

public class SimOptions extends OptionsPanel {

  private static final long serialVersionUID = 1L;
  private MyColorListener mcol = new MyColorListener();
  private JLabel TrueColorTitle = new JLabel();
  private JButton TrueColor = new JButton();
  private JLabel TrueCharTitle = new JLabel();
  private SymbolChooser TrueChar = new SymbolChooser(AppPreferences.TRUE_CHAR,"1T");
  private JLabel FalseColorTitle = new JLabel();
  private JButton FalseColor = new JButton();
  private JLabel FalseCharTitle = new JLabel();
  private SymbolChooser FalseChar = new SymbolChooser(AppPreferences.FALSE_CHAR,"0F");
  private JLabel UnknownColorTitle = new JLabel();
  private JButton UnknownColor = new JButton();
  private JLabel UnknownCharTitle = new JLabel();
  private SymbolChooser UnknownChar = new SymbolChooser(AppPreferences.UNKNOWN_CHAR,"U?Z");
  private JLabel ErrorColorTitle = new JLabel();
  private JButton ErrorColor = new JButton();
  private JLabel ErrorCharTitle = new JLabel();
  private SymbolChooser ErrorChar = new SymbolChooser(AppPreferences.ERROR_CHAR,"E!X");
  private JLabel NilColorTitle = new JLabel();
  private JButton NilColor = new JButton();
  private JLabel DontCareCharTitle = new JLabel();
  private SymbolChooser DontCareChar = new SymbolChooser(AppPreferences.DONTCARE_CHAR,"-X");
  private JLabel BusColorTitle = new JLabel();
  private JButton BusColor = new JButton();
  private JLabel HighlightColorTitle = new JLabel();
  private JButton HighlightColor = new JButton();
  private JLabel WidthErrorColorTitle = new JLabel();
  private JButton WidthErrorColor = new JButton();
  private JLabel WidthErrorCaptionColorTitle = new JLabel();
  private JButton WidthErrorCaptionColor = new JButton();
  private JLabel WidthErrorHighlightColorTitle = new JLabel();
  private JButton WidthErrorHighlightColor = new JButton();
  private JLabel WidthErrorBackgroundColorTitle = new JLabel();
  private JButton WidthErrorBackgroundColor = new JButton();
  private JButton DefaultButton = new JButton();
  private JButton ColorBlindButton = new JButton();
  private JLabel Kmap1ColorTitle = new JLabel();
  private JLabel Kmap2ColorTitle = new JLabel();
  private JLabel Kmap3ColorTitle = new JLabel();
  private JLabel Kmap4ColorTitle = new JLabel();
  private JLabel Kmap5ColorTitle = new JLabel();
  private JLabel Kmap6ColorTitle = new JLabel();
  private JLabel Kmap7ColorTitle = new JLabel();
  private JLabel Kmap8ColorTitle = new JLabel();
  private JLabel Kmap9ColorTitle = new JLabel();
  private JLabel Kmap10ColorTitle = new JLabel();
  private JLabel Kmap11ColorTitle = new JLabel();
  private JLabel Kmap12ColorTitle = new JLabel();
  private JLabel Kmap13ColorTitle = new JLabel();
  private JLabel Kmap14ColorTitle = new JLabel();
  private JLabel Kmap15ColorTitle = new JLabel();
  private JLabel Kmap16ColorTitle = new JLabel();
  private JLabel KmapColorsTitle = new JLabel("",SwingConstants.CENTER);
  private JButton Kmap1Color = new JButton();
  private JButton Kmap2Color = new JButton();
  private JButton Kmap3Color = new JButton();
  private JButton Kmap4Color = new JButton();
  private JButton Kmap5Color = new JButton();
  private JButton Kmap6Color = new JButton();
  private JButton Kmap7Color = new JButton();
  private JButton Kmap8Color = new JButton();
  private JButton Kmap9Color = new JButton();
  private JButton Kmap10Color = new JButton();
  private JButton Kmap11Color = new JButton();
  private JButton Kmap12Color = new JButton();
  private JButton Kmap13Color = new JButton();
  private JButton Kmap14Color = new JButton();
  private JButton Kmap15Color = new JButton();
  private JButton Kmap16Color = new JButton();
  private PreferencesFrame frame;
  
  private class MyListener implements PreferenceChangeListener {

    @Override
    public void preferenceChange(PreferenceChangeEvent evt) {
      boolean update = false;
      if (evt.getKey().equals(AppPreferences.TRUE_COLOR.getIdentifier())) {
        Value.TRUE_COLOR = new Color(AppPreferences.TRUE_COLOR.get());
        update = true;
      } else if (evt.getKey().equals(AppPreferences.TRUE_CHAR.getIdentifier())) {
        Value.TRUECHAR = AppPreferences.TRUE_CHAR.get().charAt(0);
        update = true;
      } else if (evt.getKey().equals(AppPreferences.FALSE_COLOR.getIdentifier())) {
        Value.FALSE_COLOR = new Color(AppPreferences.FALSE_COLOR.get());
        update = true;
      } else if (evt.getKey().equals(AppPreferences.FALSE_CHAR.getIdentifier())) {
        Value.FALSECHAR = AppPreferences.FALSE_CHAR.get().charAt(0);
        update = true;
      } else if (evt.getKey().equals(AppPreferences.UNKNOWN_COLOR.getIdentifier())) {
        Value.UNKNOWN_COLOR = new Color(AppPreferences.UNKNOWN_COLOR.get());
        update = true;
      } else if (evt.getKey().equals(AppPreferences.UNKNOWN_CHAR.getIdentifier())) {
        Value.UNKNOWNCHAR = AppPreferences.UNKNOWN_CHAR.get().charAt(0);
        update = true;
      } else if (evt.getKey().equals(AppPreferences.ERROR_COLOR.getIdentifier())) {
        Value.ERROR_COLOR = new Color(AppPreferences.ERROR_COLOR.get());
        update = true;
      } else if (evt.getKey().equals(AppPreferences.ERROR_CHAR.getIdentifier())) {
        Value.ERRORCHAR = AppPreferences.ERROR_CHAR.get().charAt(0);
        update = true;
      } else if (evt.getKey().equals(AppPreferences.NIL_COLOR.getIdentifier())) {
        Value.NIL_COLOR = new Color(AppPreferences.NIL_COLOR.get());
        update = true;
      } else if (evt.getKey().equals(AppPreferences.DONTCARE_CHAR.getIdentifier())) {
        Value.DONTCARECHAR = AppPreferences.DONTCARE_CHAR.get().charAt(0);
        update = true;
      } else if (evt.getKey().equals(AppPreferences.BUS_COLOR.getIdentifier())) {
        Value.MULTI_COLOR = new Color(AppPreferences.BUS_COLOR.get());
        update = true;
      } else if (evt.getKey().equals(AppPreferences.STROKE_COLOR.getIdentifier())) {
        Value.STROKE_COLOR = new Color(AppPreferences.STROKE_COLOR.get());
        update = true;
      } else if (evt.getKey().equals(AppPreferences.WIDTH_ERROR_COLOR.getIdentifier())) {
        Value.WIDTH_ERROR_COLOR = new Color(AppPreferences.WIDTH_ERROR_COLOR.get());
        update = true;
      } else if (evt.getKey().equals(AppPreferences.WIDTH_ERROR_CAPTION_COLOR.getIdentifier())) {
        Value.WIDTH_ERROR_CAPTION_COLOR = new Color(AppPreferences.WIDTH_ERROR_CAPTION_COLOR.get());
        update = true;
      } else if (evt.getKey().equals(AppPreferences.WIDTH_ERROR_HIGHLIGHT_COLOR.getIdentifier())) {
        Value.WIDTH_ERROR_HIGHLIGHT_COLOR = new Color(AppPreferences.WIDTH_ERROR_HIGHLIGHT_COLOR.get());
        update = true;
      } else if (evt.getKey().equals(AppPreferences.WIDTH_ERROR_BACKGROUND_COLOR.getIdentifier())) {
        Value.WIDTH_ERROR_CAPTION_BGCOLOR = new Color(AppPreferences.WIDTH_ERROR_BACKGROUND_COLOR.get());
        update = true;
      }
      if (update) {
        for (Project proj : Projects.getOpenProjects())
          proj.getFrame().repaint();
      }
    }
      
  }
  
  private class MyColorListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("default")) {
        setDefaults();
      } else if (e.getActionCommand().equals("colorblind")) {
        setColorBlind();
      } else if (e.getSource() instanceof JButton) {
        JButton but = (JButton) e.getSource();
        if (but.getIcon() instanceof ColorIcon) {
          ColorIcon i = (ColorIcon) but.getIcon();
          i.update();
        }
      }
    }
      
  }
  
  private class ColorIcon extends AbstractIcon {

    private PrefMonitor<Integer> myPref;

    public ColorIcon(PrefMonitor<Integer> myPref) {
      super();
      this.myPref = myPref;
    }

    @Override
    protected void paintIcon(Graphics2D g2) {
       g2.setColor(new Color(myPref.get()));
       g2.fillRect(0, 0, this.getIconWidth(), this.getIconHeight());
    }
    
    public void update() {
      Color col = new Color(myPref.get());
      Color newCol = ColorPickerDialog.showDialog(frame, col, false);
      if (newCol == null) return;
      if (!newCol.equals(col)) {
        col = newCol;
        myPref.set(col.getRGB());
      }
    }
  }
  
  private class SymbolChooser extends JComboBox<Character> {
    private static final long serialVersionUID = 1L;
    private PrefMonitor<String> myPref;
    private class MyactionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
        @SuppressWarnings("unchecked")
        JComboBox<Character> me = (JComboBox<Character>) e.getSource();
        Character s = (Character) me.getSelectedItem();
        if (s != myPref.get().charAt(0)) {
          myPref.set(Character.toString(s));
        }
      }
    }
    
    public SymbolChooser(PrefMonitor<String> pref , String choices) {
      super();
      myPref = pref;
      this.addActionListener(new MyactionListener());
      Character def = pref.get().charAt(0);
      int seldef = -1;
      for (int i = 0 ; i < choices.length() ; i++) {
        Character sel = choices.charAt(i);
        if (sel.equals(def)) seldef = i;
        this.addItem(sel);
      }
      if (seldef >= 0) this.setSelectedIndex(seldef);
    }
  }

  public SimOptions(PreferencesFrame window) {
    super(window); 
    frame = window;
    AppPreferences.getPrefs().addPreferenceChangeListener(new MyListener());
    GridBagConstraints c = new GridBagConstraints();
    setLayout(new GridBagLayout());
    c.insets = new Insets(2, 4, 4, 2);
    c.anchor = GridBagConstraints.CENTER;
    c.gridx = 0;
    c.gridy = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    add(TrueColorTitle,c);
    c.gridx++;
    TrueColor.addActionListener(mcol);
    TrueColor.setIcon(new ColorIcon(AppPreferences.TRUE_COLOR));
    add(TrueColor,c);
    c.gridx++;
    add(TrueCharTitle,c);
    c.gridx++;
    add(TrueChar,c);

    c.gridx = 0;
    c.gridy++;
    add(FalseColorTitle,c);
    c.gridx++;
    FalseColor.addActionListener(mcol);
    FalseColor.setIcon(new ColorIcon(AppPreferences.FALSE_COLOR));
    add(FalseColor,c);
    c.gridx++;
    add(FalseCharTitle,c);
    c.gridx++;
    add(FalseChar,c);
    
    c.gridx = 0;
    c.gridy++;
    add(UnknownColorTitle,c);
    c.gridx++;
    UnknownColor.addActionListener(mcol);
    UnknownColor.setIcon(new ColorIcon(AppPreferences.UNKNOWN_COLOR));
    add(UnknownColor,c);
    c.gridx++;
    add(UnknownCharTitle,c);
    c.gridx++;
    add(UnknownChar,c);
    
    c.gridx = 0;
    c.gridy++;
    add(ErrorColorTitle,c);
    c.gridx++;
    ErrorColor.addActionListener(mcol);
    ErrorColor.setIcon(new ColorIcon(AppPreferences.ERROR_COLOR));
    add(ErrorColor,c);
    c.gridx++;
    add(ErrorCharTitle,c);
    c.gridx++;
    add(ErrorChar,c);
    
    c.gridx = 0;
    c.gridy++;
    add(NilColorTitle,c);
    c.gridx++;
    NilColor.addActionListener(mcol);
    NilColor.setIcon(new ColorIcon(AppPreferences.NIL_COLOR));
    add(NilColor,c);
    c.gridx++;
    add(DontCareCharTitle,c);
    c.gridx++;
    add(DontCareChar,c);
    
    c.gridx = 0;
    c.gridy++;
    add(BusColorTitle,c);
    c.gridx++;
    BusColor.addActionListener(mcol);
    BusColor.setIcon(new ColorIcon(AppPreferences.BUS_COLOR));
    add(BusColor,c);
    c.gridx++;
    add(HighlightColorTitle,c);
    c.gridx++;
    HighlightColor.addActionListener(mcol);
    HighlightColor.setIcon(new ColorIcon(AppPreferences.STROKE_COLOR));
    add(HighlightColor,c);
    
    c.gridx = 0;
    c.gridy++;
    add(WidthErrorColorTitle,c);
    c.gridx++;
    WidthErrorColor.addActionListener(mcol);
    WidthErrorColor.setIcon(new ColorIcon(AppPreferences.WIDTH_ERROR_COLOR));
    add(WidthErrorColor,c);
    c.gridx++;
    add(WidthErrorCaptionColorTitle,c);
    c.gridx++;
    WidthErrorCaptionColor.addActionListener(mcol);
    WidthErrorCaptionColor.setIcon(new ColorIcon(AppPreferences.WIDTH_ERROR_CAPTION_COLOR));
    add(WidthErrorCaptionColor,c);

    c.gridx = 0;
    c.gridy++;
    add(WidthErrorHighlightColorTitle,c);
    c.gridx++;
    WidthErrorHighlightColor.addActionListener(mcol);
    WidthErrorHighlightColor.setIcon(new ColorIcon(AppPreferences.WIDTH_ERROR_HIGHLIGHT_COLOR));
    add(WidthErrorHighlightColor,c);
    c.gridx++;
    add(WidthErrorBackgroundColorTitle,c);
    c.gridx++;
    WidthErrorBackgroundColor.addActionListener(mcol);
    WidthErrorBackgroundColor.setIcon(new ColorIcon(AppPreferences.WIDTH_ERROR_BACKGROUND_COLOR));
    add(WidthErrorBackgroundColor,c);
    
    if (Main.ANALYZE) {
       c.gridx = 0;
       c.gridy++;
       c.gridwidth = 4;
       add(KmapColorsTitle,c);
       
       c.gridy++;
       c.gridwidth = 1;
       add(Kmap1ColorTitle,c);
       c.gridx++;
       Kmap1Color.addActionListener(mcol);
       Kmap1Color.setIcon(new ColorIcon(AppPreferences.KMAP1_COLOR));
       add(Kmap1Color,c);
       c.gridx++;
       add(Kmap2ColorTitle,c);
       c.gridx++;
       Kmap2Color.addActionListener(mcol);
       Kmap2Color.setIcon(new ColorIcon(AppPreferences.KMAP2_COLOR));
       add(Kmap2Color,c);

       c.gridx = 0;
       c.gridy++;
       add(Kmap3ColorTitle,c);
       c.gridx++;
       Kmap3Color.addActionListener(mcol);
       Kmap3Color.setIcon(new ColorIcon(AppPreferences.KMAP3_COLOR));
       add(Kmap3Color,c);
       c.gridx++;
       add(Kmap4ColorTitle,c);
       c.gridx++;
       Kmap4Color.addActionListener(mcol);
       Kmap4Color.setIcon(new ColorIcon(AppPreferences.KMAP4_COLOR));
       add(Kmap4Color,c);

       c.gridx = 0;
       c.gridy++;
       add(Kmap5ColorTitle,c);
       c.gridx++;
       Kmap5Color.addActionListener(mcol);
       Kmap5Color.setIcon(new ColorIcon(AppPreferences.KMAP5_COLOR));
       add(Kmap5Color,c);
       c.gridx++;
       add(Kmap6ColorTitle,c);
       c.gridx++;
       Kmap6Color.addActionListener(mcol);
       Kmap6Color.setIcon(new ColorIcon(AppPreferences.KMAP6_COLOR));
       add(Kmap6Color,c);

       c.gridx = 0;
       c.gridy++;
       add(Kmap7ColorTitle,c);
       c.gridx++;
       Kmap7Color.addActionListener(mcol);
       Kmap7Color.setIcon(new ColorIcon(AppPreferences.KMAP7_COLOR));
       add(Kmap7Color,c);
       c.gridx++;
       add(Kmap8ColorTitle,c);
       c.gridx++;
       Kmap8Color.addActionListener(mcol);
       Kmap8Color.setIcon(new ColorIcon(AppPreferences.KMAP8_COLOR));
       add(Kmap8Color,c);

       c.gridx = 0;
       c.gridy++;
       add(Kmap9ColorTitle,c);
       c.gridx++;
       Kmap9Color.addActionListener(mcol);
       Kmap9Color.setIcon(new ColorIcon(AppPreferences.KMAP9_COLOR));
       add(Kmap9Color,c);
       c.gridx++;
       add(Kmap10ColorTitle,c);
       c.gridx++;
       Kmap10Color.addActionListener(mcol);
       Kmap10Color.setIcon(new ColorIcon(AppPreferences.KMAP10_COLOR));
       add(Kmap10Color,c);

       c.gridx = 0;
       c.gridy++;
       add(Kmap11ColorTitle,c);
       c.gridx++;
       Kmap11Color.addActionListener(mcol);
       Kmap11Color.setIcon(new ColorIcon(AppPreferences.KMAP11_COLOR));
       add(Kmap11Color,c);
       c.gridx++;
       add(Kmap12ColorTitle,c);
       c.gridx++;
       Kmap12Color.addActionListener(mcol);
       Kmap12Color.setIcon(new ColorIcon(AppPreferences.KMAP12_COLOR));
       add(Kmap12Color,c);

       c.gridx = 0;
       c.gridy++;
       add(Kmap13ColorTitle,c);
       c.gridx++;
       Kmap13Color.addActionListener(mcol);
       Kmap13Color.setIcon(new ColorIcon(AppPreferences.KMAP13_COLOR));
       add(Kmap13Color,c);
       c.gridx++;
       add(Kmap14ColorTitle,c);
       c.gridx++;
       Kmap14Color.addActionListener(mcol);
       Kmap14Color.setIcon(new ColorIcon(AppPreferences.KMAP14_COLOR));
       add(Kmap14Color,c);

       c.gridx = 0;
       c.gridy++;
       add(Kmap15ColorTitle,c);
       c.gridx++;
       Kmap15Color.addActionListener(mcol);
       Kmap15Color.setIcon(new ColorIcon(AppPreferences.KMAP15_COLOR));
       add(Kmap15Color,c);
       c.gridx++;
       add(Kmap16ColorTitle,c);
       c.gridx++;
       Kmap16Color.addActionListener(mcol);
       Kmap16Color.setIcon(new ColorIcon(AppPreferences.KMAP16_COLOR));
       add(Kmap16Color,c);
    }
    
    c.gridx = 0;
    c.gridy++;
    c.gridwidth = 2;
    DefaultButton.setActionCommand("default");
    DefaultButton.addActionListener(mcol);
    add(DefaultButton,c);
    c.gridx +=2;
    ColorBlindButton.setActionCommand("colorblind");
    ColorBlindButton.addActionListener(mcol);
    add(ColorBlindButton,c);
    

    localeChanged();
 }

  @Override  
  public String getHelpText() {
    return S.get("simHelp");
  }

  @Override
  public String getTitle() {
    return S.get("simTitle");
  }

  @Override
  public void localeChanged() {
    TrueColorTitle.setText(S.get("simTrueColTitle"));
    TrueCharTitle.setText(S.get("simTrueCharTitle"));
    FalseColorTitle.setText(S.get("simFalseColTitle"));
    FalseCharTitle.setText(S.get("simFalseCharTitle"));
    UnknownColorTitle.setText(S.get("simUnknownColTitle"));
    UnknownCharTitle.setText(S.get("simUnknownCharTitle"));
    ErrorColorTitle.setText(S.get("simErrorColTitle"));
    ErrorCharTitle.setText(S.get("simErrorCharTitle"));
    NilColorTitle.setText(S.get("simNilColTitle"));
    DontCareCharTitle.setText(S.get("simDontCareCharTitle"));
    BusColorTitle.setText(S.get("simBusColTitle"));
    HighlightColorTitle.setText(S.get("simStrokeColTitle"));
    WidthErrorColorTitle.setText(S.get("simWidthErrorTitle"));
    WidthErrorCaptionColorTitle.setText(S.get("simWidthErrorCaptionTitle"));
    WidthErrorHighlightColorTitle.setText(S.get("simWidthErrorHighlightTitle"));
    WidthErrorBackgroundColorTitle.setText(S.get("simWidthErrorBackgroundTitle"));
    DefaultButton.setText(S.get("simDefaultColors"));
    ColorBlindButton.setText(S.get("simColorBlindColors"));
    Kmap1ColorTitle.setText(S.fmt("simKmapColors", 1));
    Kmap2ColorTitle.setText(S.fmt("simKmapColors", 2));
    Kmap3ColorTitle.setText(S.fmt("simKmapColors", 3));
    Kmap4ColorTitle.setText(S.fmt("simKmapColors", 4));
    Kmap5ColorTitle.setText(S.fmt("simKmapColors", 5));
    Kmap6ColorTitle.setText(S.fmt("simKmapColors", 6));
    Kmap7ColorTitle.setText(S.fmt("simKmapColors", 7));
    Kmap8ColorTitle.setText(S.fmt("simKmapColors", 8));
    Kmap9ColorTitle.setText(S.fmt("simKmapColors", 9));
    Kmap10ColorTitle.setText(S.fmt("simKmapColors", 10));
    Kmap11ColorTitle.setText(S.fmt("simKmapColors", 11));
    Kmap12ColorTitle.setText(S.fmt("simKmapColors", 12));
    Kmap13ColorTitle.setText(S.fmt("simKmapColors", 13));
    Kmap14ColorTitle.setText(S.fmt("simKmapColors", 14));
    Kmap15ColorTitle.setText(S.fmt("simKmapColors", 15));
    Kmap16ColorTitle.setText(S.fmt("simKmapColors", 16));
    KmapColorsTitle.setText(S.get("simKmapColorsTitle"));
  }
  
  private void setDefaults() {
    AppPreferences.TRUE_COLOR.set(0x0000D300);
    AppPreferences.FALSE_COLOR.set(0x00006500);
    AppPreferences.UNKNOWN_COLOR.set(0x002827FF);
    AppPreferences.ERROR_COLOR.set(0x00C10000);
    AppPreferences.NIL_COLOR.set(0x818181);
    AppPreferences.BUS_COLOR.set(1);
    AppPreferences.STROKE_COLOR.set(0xFE00FF);
    AppPreferences.WIDTH_ERROR_COLOR.set(0xFF7A00);
    AppPreferences.WIDTH_ERROR_CAPTION_COLOR.set(0x560000);
    AppPreferences.WIDTH_ERROR_HIGHLIGHT_COLOR.set(0xFFFE00);
    AppPreferences.WIDTH_ERROR_BACKGROUND_COLOR.set(0xFFE6D2);
    AppPreferences.KMAP1_COLOR.set(0x810000);
    AppPreferences.KMAP2_COLOR.set(0xE7194B);
    AppPreferences.KMAP3_COLOR.set(0xFABEBF);
    AppPreferences.KMAP4_COLOR.set(0xAA6E29);
    AppPreferences.KMAP5_COLOR.set(0xF58231);
    AppPreferences.KMAP6_COLOR.set(0xFFD7B5);
    AppPreferences.KMAP7_COLOR.set(0x818000);
    AppPreferences.KMAP8_COLOR.set(0xFFFF1A);
    AppPreferences.KMAP9_COLOR.set(0xD2F53D);
    AppPreferences.KMAP10_COLOR.set(0x000081);
    AppPreferences.KMAP11_COLOR.set(0x911EB5);
    AppPreferences.KMAP12_COLOR.set(0x3CB5AF);
    AppPreferences.KMAP13_COLOR.set(0x0082CC);
    AppPreferences.KMAP14_COLOR.set(0xE7BEFF);
    AppPreferences.KMAP15_COLOR.set(0xAAFFC4);
    AppPreferences.KMAP16_COLOR.set(0xF032E7);
    repaint();
  }

  private void setColorBlind() {
    AppPreferences.TRUE_COLOR.set(0xF4EB42);
    AppPreferences.FALSE_COLOR.set(0x203BE8);
    AppPreferences.UNKNOWN_COLOR.set(0x01BC9D);
    AppPreferences.ERROR_COLOR.set(0x00C10000);
    AppPreferences.NIL_COLOR.set(0x818181);
    AppPreferences.BUS_COLOR.set(1);
    AppPreferences.STROKE_COLOR.set(0xBBBBBB);
    AppPreferences.WIDTH_ERROR_COLOR.set(0xC413DB);
    AppPreferences.WIDTH_ERROR_CAPTION_COLOR.set(0x560000);
    AppPreferences.WIDTH_ERROR_HIGHLIGHT_COLOR.set(0xFFFE00);
    AppPreferences.WIDTH_ERROR_BACKGROUND_COLOR.set(0xFFE6D2);
    AppPreferences.KMAP1_COLOR.set(0x490092);
    AppPreferences.KMAP2_COLOR.set(0x920000);
    AppPreferences.KMAP3_COLOR.set(0x004949);
    AppPreferences.KMAP4_COLOR.set(0x006DDB);
    AppPreferences.KMAP5_COLOR.set(0x924900);
    AppPreferences.KMAP6_COLOR.set(0x009292);
    AppPreferences.KMAP7_COLOR.set(0xB66DFF);
    AppPreferences.KMAP8_COLOR.set(0xDBD100);
    AppPreferences.KMAP9_COLOR.set(0xFF6DB6);
    AppPreferences.KMAP10_COLOR.set(0x6DB6FF);
    AppPreferences.KMAP11_COLOR.set(0x24FF24);
    AppPreferences.KMAP12_COLOR.set(0xFFB677);
    AppPreferences.KMAP13_COLOR.set(0xB6DBFF);
    AppPreferences.KMAP14_COLOR.set(0xFFFF6D);
    AppPreferences.KMAP15_COLOR.set(0x009292);
    AppPreferences.KMAP16_COLOR.set(0xFFB677);
    repaint();
  }
}
