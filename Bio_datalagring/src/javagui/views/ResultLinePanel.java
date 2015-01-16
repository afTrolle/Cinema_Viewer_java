package javagui.views;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JSeparator;
import java.awt.Insets;

public class ResultLinePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ResultLinePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0,0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0,0};
		setLayout(gridBagLayout);
		
		JLabel lblBiografen = new JLabel("Biografen");
		GridBagConstraints gbc_lblBiografen = new GridBagConstraints();
		gbc_lblBiografen.insets = new Insets(0, 0, 5, 5);
		gbc_lblBiografen.gridx = 0;
		gbc_lblBiografen.gridy = 0;
		add(lblBiografen, gbc_lblBiografen);
		
		JLabel lblSalong = new JLabel("Salong");
		GridBagConstraints gbc_lblSalong = new GridBagConstraints();
		gbc_lblSalong.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalong.gridx = 1;
		gbc_lblSalong.gridy = 0;
		add(lblSalong, gbc_lblSalong);
		
		JLabel lblTid = new JLabel("Tid");
		GridBagConstraints gbc_lblTid = new GridBagConstraints();
		gbc_lblTid.insets = new Insets(0, 0, 5, 5);
		gbc_lblTid.gridx = 2;
		gbc_lblTid.gridy = 0;
		add(lblTid, gbc_lblTid);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridwidth = 4;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);

	}

}
