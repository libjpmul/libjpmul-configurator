package no.ntnu.acp142.configui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.UnknownHostException;

/*
 * Copyright (c) 2013, Luka Cetusic, Thomas Martin Schmid
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 * (1) Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 
 * (2) Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 * 
 * (3) The name of the author may not be used to
 * endorse or promote products derived from this software without
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * ConfigPanel is the user interface that allows users to configure parameters
 * if needed.
 * 
 * @author Luka Cetusic, Thomas Martin Schmid
 */
public class ConfigPanel extends JScrollPane implements PropertyChangeListener, ActionListener {

    private static final long  serialVersionUID = 9083838699335375164L;

    private GridBagConstraints c;
    private JButton            buttonConfigClear;
    private JButton            buttonConfigApply;
    private JPanel             thePanel;

    // --- [JTEXTFIELD CONFIGURATION PARAMETERS] ---------------------------- //
    // B01 Predefined Protocol Parameters
    private JTextField         waitForRejectTime;
    private JTextField         announceDelay;
    private JTextField         announceCt;
    private JTextField         ackRetransmissionTime;
    private JTextField         backoffFactor;
    private JTextField         emconRtc;
    private JTextField         emconRti;
    private JTextField         mm;
    private JTextField         ackPduTime;
    // B02-B03 Multicast group address and port numbers
    private JTextField         gg;
    private JTextField         tPort;
    private JTextField         rPort;
    private JTextField         dPort;
    private JTextField         aPort;
    private JTextField         multicastRangeStart;
    private JTextField         multicastRangeEnd;
    // Undefined, but useful, parameters
    private JTextField         pduMaxSize;
    private JTextField         pduExpiryTime;
    private JTextField         nodeId;
    private JTextField         ackDelayUpperBound;
    private JTextField         dataAndAddressPduSendDelay;
    // ---------------------------------------------------------------------- //

    // --- [JTEXTFIELD CHAT CONFIGURATION PARAMETERS] ----------------------- //
    private JTextField         broadcastGroup;
    private JTextField         broadcastPort;
    private JTextField         maximumWaitForResponseOnDelayedSend;
    private JTextField         defaultTimeToLive;
    private JTextField         waitForInUseResponse;
    // ---------------------------------------------------------------------- //

    // --- [JLABEL CONFIGURATION GROUPS] ------------------------------------ //
    private JLabel             labelProtocolParameters;
    private JLabel             labelMulticastGroupAndPortNumbers;
    private JLabel             labelOtherUsefulParameters;
    // ---------------------------------------------------------------------- //

    // --- [JLABEL PARAMETERS] ---------------------------------------------- //
    // B01 Predefined Protocol Parameters
    private JLabel             labelWaitForRejectTime;
    private JLabel             labelAnnounceDelay;
    private JLabel             labelAnnounceCt;
    private JLabel             labelAckRetransmissionTime;
    private JLabel             labelBackoffFactor;
    private JLabel             labelEmconRtc;
    private JLabel             labelEmconRti;
    private JLabel             labelMm;
    private JLabel             labelAckPduTime;
    // B02-B03 Multicast group address and port numbers
    private JLabel             labelGg;
    private JLabel             labelTPort;
    private JLabel             labelRPort;
    private JLabel             labelDPort;
    private JLabel             labelAPort;
    private JLabel             labelMulticastRangeStart;
    private JLabel             labelMulticastRangeEnd;
    // Undefined, but useful, parameters
    private JLabel             labelPduMaxSize;
    private JLabel             labelPduExpiryTime;
    private JLabel             labelNodeId;
    private JLabel             labelAckDelayUpperBound;
    private JLabel             labelDataAndAddressPduSendDelay;
    // --------------------------------------------------------------------- //

    // TITLE LABEL FOR CHAT CONFIGURATION
    private JLabel             labelChatConfiguration;

    // --- [JLABEL CHAT CONFIGURATION PARAMETERS] -------------------------- //
    private JLabel             labelBroadcastGroup;
    private JLabel             labelBroadcastPort;
    private JLabel             labelMaximumWaitForResponseOnDelayedSend;
    private JLabel             labelDefaultTimeToLive;
    private JLabel             labelWaitForInUseResponse;

    // ---------------------------------------------------------------------- //

    /**
     * Constructor that initializes and adds the elements on panel.
     **/
    public ConfigPanel() {

        super();

        // INITIALIZE
        thePanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        buttonConfigClear = new JButton("Clear");
        buttonConfigApply = new JButton("Apply");

        // B01 Predefined Protocol Parameters
        waitForRejectTime = new JTextField();
        announceDelay = new JTextField();
        announceCt = new JTextField();
        ackRetransmissionTime = new JTextField();
        backoffFactor = new JTextField();
        emconRtc = new JTextField();
        emconRti = new JTextField();
        mm = new JTextField();
        ackPduTime = new JTextField();

        // B02-B03 Multicast group address and port numbers
        gg = new JTextField();
        tPort = new JTextField();
        rPort = new JTextField();
        dPort = new JTextField();
        aPort = new JTextField();
        multicastRangeStart = new JTextField();
        multicastRangeEnd = new JTextField();

        // Undefined, but useful, parameters
        pduMaxSize = new JTextField();
        pduExpiryTime = new JTextField();
        nodeId = new JTextField();
        ackDelayUpperBound = new JTextField();
        dataAndAddressPduSendDelay = new JTextField();

        // Chat configuration
        broadcastGroup = new JTextField();
        broadcastPort = new JTextField();
        maximumWaitForResponseOnDelayedSend = new JTextField();
        defaultTimeToLive = new JTextField();
        waitForInUseResponse = new JTextField();

        labelProtocolParameters = new JLabel("Protocol Parameters");
        labelMulticastGroupAndPortNumbers = new JLabel("Multicast Group and Port Numbers");
        labelOtherUsefulParameters = new JLabel("Other Useful Parameters");

        // B01 Predefined Protocol Parameters
        labelWaitForRejectTime = new JLabel("Wait for reject time:");
        labelAnnounceDelay = new JLabel("Announce delay:");
        labelAnnounceCt = new JLabel("Announce CT:");
        labelAckRetransmissionTime = new JLabel("Ack retransmission time:");
        labelBackoffFactor = new JLabel("Backoff factor:");
        labelEmconRtc = new JLabel("Emcon RTC:");
        labelEmconRti = new JLabel("Emcon RTI:");
        labelMm = new JLabel("MM:");
        labelAckPduTime = new JLabel("Ack PDU time:");

        // B02-B03 Multicast group address and port numbers
        labelGg = new JLabel("GG:");
        labelTPort = new JLabel("T port:");
        labelRPort = new JLabel("R port:");
        labelDPort = new JLabel("D port:");
        labelAPort = new JLabel("A port:");
        labelMulticastRangeStart = new JLabel("Multicast range start:");
        labelMulticastRangeEnd = new JLabel("Multicast range end:");

        // Undefined, but useful, parameters
        labelPduMaxSize = new JLabel("PDU max size:");
        labelPduExpiryTime = new JLabel("PDU expiry time:");
        labelNodeId = new JLabel("Node id:");
        labelAckDelayUpperBound = new JLabel("Ack delay upper bound:");
        labelDataAndAddressPduSendDelay = new JLabel("Data and address PDU send delay:");

        // Chat Configuration
        labelChatConfiguration = new JLabel("Chat Configuration:");
        labelBroadcastGroup = new JLabel("Broadcast group:");
        labelBroadcastPort = new JLabel("Broadcast port:");
        labelMaximumWaitForResponseOnDelayedSend = new JLabel("Max wait for response on delayed send:");
        labelDefaultTimeToLive = new JLabel("Default time to live:");
        labelWaitForInUseResponse = new JLabel("Wait for in use response:");

        // SET ACTIONLISTENERS
        buttonConfigApply.addActionListener(this);
        buttonConfigClear.addActionListener(this);

        // SET SIZE
        buttonConfigClear.setPreferredSize(new Dimension(80, 25));
        buttonConfigApply.setPreferredSize(new Dimension(80, 25));

        // B01 Predefined Protocol Parameters
        waitForRejectTime.setPreferredSize(new Dimension(200, 25));
        announceDelay.setPreferredSize(new Dimension(200, 25));
        announceCt.setPreferredSize(new Dimension(200, 25));
        ackRetransmissionTime.setPreferredSize(new Dimension(200, 25));
        backoffFactor.setPreferredSize(new Dimension(200, 25));
        emconRtc.setPreferredSize(new Dimension(200, 25));
        emconRti.setPreferredSize(new Dimension(200, 25));
        mm.setPreferredSize(new Dimension(200, 25));
        ackPduTime.setPreferredSize(new Dimension(200, 25));
        // B02-B03 Multicast group address and port numbers
        gg.setPreferredSize(new Dimension(200, 25));
        tPort.setPreferredSize(new Dimension(200, 25));
        rPort.setPreferredSize(new Dimension(200, 25));
        dPort.setPreferredSize(new Dimension(200, 25));
        aPort.setPreferredSize(new Dimension(200, 25));
        multicastRangeStart.setPreferredSize(new Dimension(200, 25));
        multicastRangeEnd.setPreferredSize(new Dimension(200, 25));
        // Undefined, but useful, parameters
        pduMaxSize.setPreferredSize(new Dimension(200, 25));
        pduExpiryTime.setPreferredSize(new Dimension(200, 25));
        nodeId.setPreferredSize(new Dimension(200, 25));
        ackDelayUpperBound.setPreferredSize(new Dimension(200, 25));
        dataAndAddressPduSendDelay.setPreferredSize(new Dimension(200, 25));

        // Chat configuration
        broadcastGroup.setPreferredSize(new Dimension(200, 25));
        broadcastPort.setPreferredSize(new Dimension(200, 25));
        maximumWaitForResponseOnDelayedSend.setPreferredSize(new Dimension(200, 25));
        defaultTimeToLive.setPreferredSize(new Dimension(200, 25));
        waitForInUseResponse.setPreferredSize(new Dimension(200, 25));

        // SET FONT
        Font fontTitleGroups = new Font("Calibri", Font.BOLD, 18);
        Font fontParameters = new Font("Calibri", Font.PLAIN, 14);
        // Title labels
        labelProtocolParameters.setFont(fontTitleGroups);
        labelMulticastGroupAndPortNumbers.setFont(fontTitleGroups);
        labelOtherUsefulParameters.setFont(fontTitleGroups);
        // Parameter labels
        labelWaitForRejectTime.setFont(fontParameters);
        labelAnnounceDelay.setFont(fontParameters);
        labelAnnounceCt.setFont(fontParameters);
        labelAckRetransmissionTime.setFont(fontParameters);
        labelBackoffFactor.setFont(fontParameters);
        labelEmconRtc.setFont(fontParameters);
        labelEmconRti.setFont(fontParameters);
        labelMm.setFont(fontParameters);
        labelAckPduTime.setFont(fontParameters);
        labelGg.setFont(fontParameters);
        labelTPort.setFont(fontParameters);
        labelRPort.setFont(fontParameters);
        labelDPort.setFont(fontParameters);
        labelAPort.setFont(fontParameters);
        labelMulticastRangeStart.setFont(fontParameters);
        labelMulticastRangeEnd.setFont(fontParameters);
        labelPduMaxSize.setFont(fontParameters);
        labelPduExpiryTime.setFont(fontParameters);
        labelNodeId.setFont(fontParameters);
        labelAckDelayUpperBound.setFont(fontParameters);
        labelDataAndAddressPduSendDelay.setFont(fontParameters);

        // Chat Configurations
        labelChatConfiguration.setFont(fontTitleGroups);
        labelBroadcastGroup.setFont(fontParameters);
        labelBroadcastPort.setFont(fontParameters);
        labelMaximumWaitForResponseOnDelayedSend.setFont(fontParameters);
        labelDefaultTimeToLive.setFont(fontParameters);
        labelWaitForInUseResponse.setFont(fontParameters);

        // SET DESCRIPTION TO LABEL
        labelWaitForRejectTime.setToolTipText("Time between sending a Request_PDU and an affiliated Announce_PDU.");
        labelAnnounceDelay.setToolTipText("<html>Time between sending an Announce_PDU and the first<br>"
                + " affiliated Address_PDU.</html>");
        labelAnnounceCt.setToolTipText("The number of times the Announce_PDU is transmitted.");
        labelAckRetransmissionTime.setToolTipText("<html>Time a transmitter waits before re-transmitting<br> "
                + "a message to receivers not in EMCON, if no<br>" + " acknowledgment received.</html>");
        labelBackoffFactor.setToolTipText("<html>Multiplying factor applied to ACK_RE-TRANSMISSION_TIME<br> "
                + "on subsequent re-transmissions, to achieve exponentially increasing delay.</html>");
        labelEmconRtc.setToolTipText("<html>Re-transmission count - Maximum number of message<br>"
                + " re-transmission for receivers in EMCON.</html>");
        labelEmconRti.setToolTipText("<html>Re-transmission interval - Time in seconds a transmitter<br>"
                + " waits before re-transmitting a message for receivers in EMCON.</html> ");
        labelMm.setToolTipText("<html>Maximum number of new entries in the list of <br>"
                + "Missing_Data_PDU_Seq_Numbers field in an<br>" + " Ack_Info_Entry of an Ack_PDU.</html>");
        labelAckPduTime.setToolTipText("<html>Time a receiver waits before re-transmitting Ack_PDU(s)<br>"
                + " if no response is received from the transmitting node.</html>");
        labelGg.setToolTipText("<html>Multicast group address to which all nodes should join<br>"
                + " in order to allow dynamic building of multicast groups.</html>");
        labelTPort.setToolTipText("<html>Port number used for the transmission of Request_PDUs,<br>"
                + " Reject_PDUs and Release_PDUs between the transmission programs.<br>"
                + " All transmitter processes have to listen to this port<br>"
                + " in conjunction with the multicast group GG.</html> ");
        labelRPort.setToolTipText("<html>Port number used by the transmitters to send the Announce_PDUs,<br>"
                + " informing those receivers involved in the concerning message<br>"
                + " transfer to join a specific multicast group.<br>"
                + " All receiver processes have to listen to this port<br>"
                + " in conjunction with the multicast group GG.</html> ");
        labelDPort.setToolTipText("<html>Port used for the data traffic from the Message Transmitter<br>"
                + " of Multicast_OUT to the Message Receiver of Multicast_IN.</html> ");
        labelAPort.setToolTipText("<html>Port used for the traffic from the Message Receiver<br>"
                + " of Multicast_IN to the message Transmitter of Multicast_OUT.</html>");
        labelMulticastRangeStart.setToolTipText("Start address of the multicast pool.");
        labelMulticastRangeEnd.setToolTipText("End address of the multicast pool.");
        labelPduMaxSize.setToolTipText("Sets the maximum size of a PDU.");
        labelPduExpiryTime.setToolTipText("Sets default timeout value of a PDU.");
        labelNodeId.setToolTipText("Sets ID of this node.");
        labelAckDelayUpperBound.setToolTipText("Sets the upper bound on the sleep delay when sending Ack PDUs.");
        labelDataAndAddressPduSendDelay
                .setToolTipText("The delay between sending the address PDU and data PDU, as well as between each data PDU.");
        // Chat configurations
        labelBroadcastGroup.setToolTipText("Multicast group to used to share lists of the users in coordinationGroup.");
        labelBroadcastPort.setToolTipText("Port to listen to broadcastGroup on");
        labelMaximumWaitForResponseOnDelayedSend
                .setToolTipText("When we wait for responses before sending , we wait a random amount of time, from 0 to this amount of milliseconds");
        labelDefaultTimeToLive.setToolTipText("Default time to live of ACP142 messages.");
        labelWaitForInUseResponse.setToolTipText("Time to wait for a response before deleting a topic.");

        // ADD TO CONFIGURATION PANEL (JLabels)
        // B01 Predefined Protocol Parameters
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 5, 5, 10);
        c.gridx = 0;
        c.gridy = 0;
        thePanel.add(labelProtocolParameters, c);

        // WAIT FOR REJECT TIME
        c.gridx = 0;
        c.gridy = 1;
        thePanel.add(labelWaitForRejectTime, c);

        // ANNOUNCE DELAY
        c.gridx = 0;
        c.gridy = 2;
        thePanel.add(labelAnnounceDelay, c);

        // ANNOUNCE CT
        c.gridx = 0;
        c.gridy = 3;
        thePanel.add(labelAnnounceCt, c);

        // ACKRETRANSMISSION TIME
        c.gridx = 0;
        c.gridy = 4;
        thePanel.add(labelAckRetransmissionTime, c);

        // BACKOFF FACTOR
        c.gridx = 0;
        c.gridy = 5;
        thePanel.add(labelBackoffFactor, c);

        // EMCON RTC
        c.gridx = 0;
        c.gridy = 6;
        thePanel.add(labelEmconRtc, c);

        // EMCON RTI
        c.gridx = 0;
        c.gridy = 7;
        thePanel.add(labelEmconRti, c);

        // MM
        c.gridx = 0;
        c.gridy = 8;
        thePanel.add(labelMm, c);

        // ACK PDU TIME
        c.gridx = 0;
        c.gridy = 9;
        thePanel.add(labelAckPduTime, c);

        // B02-B03 Multicast group address and port numbers
        c.insets = new Insets(20, 5, 5, 10);
        c.gridx = 0;
        c.gridy = 10;
        thePanel.add(labelMulticastGroupAndPortNumbers, c);

        // GG
        c.insets = new Insets(10, 5, 5, 10);
        c.gridx = 0;
        c.gridy = 11;
        thePanel.add(labelGg, c);

        // T PORT
        c.gridx = 0;
        c.gridy = 12;
        thePanel.add(labelTPort, c);

        // R PORT
        c.gridx = 0;
        c.gridy = 13;
        thePanel.add(labelRPort, c);

        // D PORT
        c.gridx = 0;
        c.gridy = 14;
        thePanel.add(labelDPort, c);

        // A PORT
        c.gridx = 0;
        c.gridy = 15;
        thePanel.add(labelAPort, c);

        // MULTICAST RANGE START
        c.gridx = 0;
        c.gridy = 16;
        thePanel.add(labelMulticastRangeStart, c);

        // MULTICAST RANGE END
        c.gridx = 0;
        c.gridy = 17;
        thePanel.add(labelMulticastRangeEnd, c);

        // Undefined, but useful, parameters
        c.insets = new Insets(20, 5, 5, 10);
        c.gridx = 0;
        c.gridy = 18;
        thePanel.add(labelOtherUsefulParameters, c);

        // PDU MAX SIZE
        c.insets = new Insets(10, 5, 5, 10);
        c.gridx = 0;
        c.gridy = 19;
        thePanel.add(labelPduMaxSize, c);

        // PDU EXPIRY TIME
        c.gridx = 0;
        c.gridy = 20;
        thePanel.add(labelPduExpiryTime, c);

        // NODE ID
        c.gridx = 0;
        c.gridy = 21;
        thePanel.add(labelNodeId, c);

        // ACK DELAY UPPER BOUND
        c.gridx = 0;
        c.gridy = 22;
        thePanel.add(labelAckDelayUpperBound, c);

        // DATA AND ADDRESS PDU SEND DELAY
        c.gridx = 0;
        c.gridy = 23;
        thePanel.add(labelDataAndAddressPduSendDelay, c);

        // ADD TO CONFIGURATION PANEL (JTextField)
        // B01 Predefined Protocol Parameters
        // WAIT FOR REJECT TIME
        c.insets = new Insets(10, 5, 5, 10);
        c.gridx = 1;
        c.gridy = 1;
        thePanel.add(waitForRejectTime, c);

        // ANNOUNCE DELAY
        c.gridx = 1;
        c.gridy = 2;
        thePanel.add(announceDelay, c);

        // ANNOUNCE CT
        c.gridx = 1;
        c.gridy = 3;
        thePanel.add(announceCt, c);

        // ACKRETRANSMISSION TIME
        c.gridx = 1;
        c.gridy = 4;
        thePanel.add(ackRetransmissionTime, c);

        // BACKOFF FACTOR
        c.gridx = 1;
        c.gridy = 5;
        thePanel.add(backoffFactor, c);

        // EMCON RTC
        c.gridx = 1;
        c.gridy = 6;
        thePanel.add(emconRtc, c);

        // EMCON RTC
        c.gridx = 1;
        c.gridy = 7;
        thePanel.add(emconRti, c);

        // MM
        c.gridx = 1;
        c.gridy = 8;
        thePanel.add(mm, c);

        // ACK PDU TIME
        c.gridx = 1;
        c.gridy = 9;
        thePanel.add(ackPduTime, c);

        // B02-B03 Multicast group address and port numbers
        // GG
        c.gridx = 1;
        c.gridy = 11;
        thePanel.add(gg, c);

        // T PORT
        c.gridx = 1;
        c.gridy = 12;
        thePanel.add(tPort, c);

        // R PORT
        c.gridx = 1;
        c.gridy = 13;
        thePanel.add(rPort, c);

        // D PORT
        c.gridx = 1;
        c.gridy = 14;
        thePanel.add(dPort, c);

        // A PORT
        c.gridx = 1;
        c.gridy = 15;
        thePanel.add(aPort, c);

        // MULTICAST RANGE START
        c.gridx = 1;
        c.gridy = 16;
        thePanel.add(multicastRangeStart, c);

        // MULTICAST RANGE END
        c.gridx = 1;
        c.gridy = 17;
        thePanel.add(multicastRangeEnd, c);

        // Undefined, but useful, parameters
        // PDU MAX SIZE
        c.gridx = 1;
        c.gridy = 19;
        thePanel.add(pduMaxSize, c);

        // PDU EXPIRY TIME
        c.gridx = 1;
        c.gridy = 20;
        thePanel.add(pduExpiryTime, c);

        // NODE ID
        c.gridx = 1;
        c.gridy = 21;
        thePanel.add(nodeId, c);

        // ACK DELAY UPPER BOUND
        c.gridx = 1;
        c.gridy = 22;
        thePanel.add(ackDelayUpperBound, c);

        // DATA AND ADDRESS PDU SEND DELAY
        c.gridx = 1;
        c.gridy = 23;
        thePanel.add(dataAndAddressPduSendDelay, c);

        // JBUTTON CLEAR
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 24;
        thePanel.add(buttonConfigClear, c);

        // JBUTTON APPLY
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 1;
        c.gridy = 24;
        thePanel.add(buttonConfigApply, c);

        // SET DEFAULT MODEL TO THE ELEMENTS
        setModelConfiguration();
        setModelChatConfiguration();

        this.setViewportView(thePanel);
    }

    /**
     * setModel method sets the default configuration model to configView by
     * getting all the data from the class ConfigurationModel.
     */
    public void setModelConfiguration() {

        // configModel.addPropertyChangeListener(this);

        // PARAMETERS GETS SET FROM CONFIGURATIONMODEL
        // B01 Predefined Protocol Parameters
        waitForRejectTime.setText(ConfigurationModel.getWaitForRejectTime());
        announceDelay.setText(ConfigurationModel.getAnnounceDelay());
        announceCt.setText(ConfigurationModel.getAnnounceCt());
        ackRetransmissionTime.setText(ConfigurationModel.getAckRetransmissionTime());
        backoffFactor.setText(ConfigurationModel.getBackoffFactor());
        emconRtc.setText(ConfigurationModel.getEmconRtc());
        emconRti.setText(ConfigurationModel.getEmconRti());
        mm.setText(ConfigurationModel.getMm());
        ackPduTime.setText(ConfigurationModel.getAckPduTime());
        // B02-B03 Multicast group address and port numbers
        gg.setText(ConfigurationModel.getGg());
        tPort.setText(ConfigurationModel.getTPort());
        rPort.setText(ConfigurationModel.getRPort());
        dPort.setText(ConfigurationModel.getDPort());
        aPort.setText(ConfigurationModel.getAPort());
        multicastRangeStart.setText(ConfigurationModel.getMulticastStartRange());
        multicastRangeEnd.setText(ConfigurationModel.getMulticastEndRange());
        // Undefined, but useful, parameters
        pduMaxSize.setText(ConfigurationModel.getPduMaxSize());
        pduExpiryTime.setText(ConfigurationModel.getUndefinedPduExpiryTime());
        nodeId.setText(ConfigurationModel.getNodeId());
        ackDelayUpperBound.setText(ConfigurationModel.getAckDelayUpperBound());
        dataAndAddressPduSendDelay.setText(ConfigurationModel.getDataAndAdressPduSendDelay());
    }

    public void setModelChatConfiguration() {
        // Chat configuration
        broadcastGroup.setText(ConfigurationModel.getBroadcastGroup().getHostAddress());
        broadcastPort.setText(Short.toString(ConfigurationModel.getBroadcastPort()));
        maximumWaitForResponseOnDelayedSend.setText(Long.toString(ConfigurationModel
                .getMaximumWaitForResponseOnDelayedSend()));
        defaultTimeToLive.setText(Long.toString(ConfigurationModel.getDefaultTimeToLive()));
        waitForInUseResponse.setText(Long.toString(ConfigurationModel.getWaitForInUseResponse()));

    }

    /**
     * getConfigurationPanel gets the configuration panel from ConfigView
     * 
     * @return configuration panel
     */
    public JPanel getConfigurationPanel() {
        return thePanel;
    }

    /**
     * applyChangesConfiguration adds all changes to the ConfigurationModel when
     * Apply button is clicked.
     * 
     * @throws UnknownHostException
     */
    public void applyChangesConfiguration() throws UnknownHostException {
        ConfigurationModel.setWaitForRejectTime(Integer.parseInt(waitForRejectTime.getText()));
        ConfigurationModel.setAnnounceDelay(Integer.parseInt(announceDelay.getText()));
        ConfigurationModel.setAnnounceCt(Integer.parseInt(announceCt.getText()));
        ConfigurationModel.setAckRetransmissionTime(Integer.parseInt(ackRetransmissionTime.getText()));
        ConfigurationModel.setBackoffFactor(Double.parseDouble(backoffFactor.getText()));
        ConfigurationModel.setEmconRtc(Integer.parseInt(emconRtc.getText()));
        ConfigurationModel.setEmconRti(Integer.parseInt(emconRti.getText()));
        ConfigurationModel.setMm(Integer.parseInt(mm.getText()));
        ConfigurationModel.setAckPduTime(Integer.parseInt(ackPduTime.getText()));
        ConfigurationModel.setGg(gg.getText());
        ConfigurationModel.setTPort(Integer.parseInt(tPort.getText()));
        ConfigurationModel.setRPort(Integer.parseInt(rPort.getText()));
        ConfigurationModel.setDPort(Integer.parseInt(dPort.getText()));
        ConfigurationModel.setAPort(Integer.parseInt(aPort.getText()));
        ConfigurationModel.setMulticastStartRange(multicastRangeStart.getText());
        ConfigurationModel.setMulticastEndRange(multicastRangeEnd.getText());
        ConfigurationModel.setPduMaxSize(Integer.parseInt(pduMaxSize.getText()));
        ConfigurationModel.setUndefinedPduExpiryTime(Integer.parseInt(pduExpiryTime.getText()));
        ConfigurationModel.setNodeId(Integer.parseInt(nodeId.getText()));
        ConfigurationModel.setAckDelayUpperBound(Long.parseLong(ackDelayUpperBound.getText()));
        ConfigurationModel.setDataAndAdressPduSendDelay(Integer.parseInt(dataAndAddressPduSendDelay.getText()));

    }

    /**
     * applyChangesConfiguration adds all changes to the ConfigurationModel when
     * Apply button is clicked.
     */
    public void applyChangesChatConfiguration() {
        ConfigurationModel.setBroadcastGroup(broadcastGroup.getText());
        ConfigurationModel.setBroadcastPort(Short.parseShort(broadcastPort.getText()));
        ConfigurationModel.setMaximumWaitForResponseOnDelayedSend(Long.parseLong(maximumWaitForResponseOnDelayedSend
                .getText()));
        ConfigurationModel.setDefaultTimeToLive(Long.parseLong(defaultTimeToLive.getText()));
        ConfigurationModel.setWaitForInUseResponse(Long.parseLong(waitForInUseResponse.getText()));
    }

    /**
     * propertyChange listens if there is a change in the view that needs to get
     * updated from the ConfigurationModel.
     * 
     * @param evt
     *            name of event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String name = evt.getPropertyName();
        if ( name.equals(ConfigurationModel.WAIT_FOR_REJECT_TIME_PROPERTY) ) {
            waitForRejectTime.setText(ConfigurationModel.getWaitForRejectTime());
        }
        if ( name.equals(ConfigurationModel.ANNOUNCE_DELAY_PROPERTY) ) {
            announceDelay.setText(ConfigurationModel.getAnnounceDelay());
        }
        if ( name.equals(ConfigurationModel.ANNOUNCE_CT_PROPERTY) ) {
            announceCt.setText(ConfigurationModel.getAnnounceCt());
        }
        if ( name.equals(ConfigurationModel.ACK_RETRANSMISSION_TIME_PROPERTY) ) {
            ackRetransmissionTime.setText(ConfigurationModel.getAckRetransmissionTime());
        }
        if ( name.equals(ConfigurationModel.BACKOFF_FACTOR_PROPERTY) ) {
            backoffFactor.setText(ConfigurationModel.getBackoffFactor());
        }
        if ( name.equals(ConfigurationModel.EMCON_RTC_PROPERTY) ) {
            emconRtc.setText(ConfigurationModel.getEmconRtc());
        }
        if ( name.equals(ConfigurationModel.EMCON_RTI_PROPERTY) ) {
            emconRti.setText(ConfigurationModel.getEmconRti());
        }
        if ( name.equals(ConfigurationModel.MM_PROPERTY) ) {
            mm.setText(ConfigurationModel.getMm());
        }
        if ( name.equals(ConfigurationModel.ACK_PDU_TIME_PROPERTY) ) {
            ackPduTime.setText(ConfigurationModel.getAckPduTime());
        }
        if ( name.equals(ConfigurationModel.GG_PROPERTY) ) {
            gg.setText(ConfigurationModel.getGg());
        }
        if ( name.equals(ConfigurationModel.T_PORT_PROPERTY) ) {
            tPort.setText(ConfigurationModel.getTPort());
        }
        if ( name.equals(ConfigurationModel.R_PORT_PROPERTY) ) {
            rPort.setText(ConfigurationModel.getRPort());
        }
        if ( name.equals(ConfigurationModel.D_PORT_PROPERTY) ) {
            dPort.setText(ConfigurationModel.getDPort());
        }
        if ( name.equals(ConfigurationModel.A_PORT_PROPERTY) ) {
            aPort.setText(ConfigurationModel.getAPort());
        }
        if ( name.equals(ConfigurationModel.MULTICAST_RANGE_START_PROPERTY) ) {
            multicastRangeStart.setText(ConfigurationModel.getMulticastStartRange());
        }
        if ( name.equals(ConfigurationModel.MULTICAST_RANGE_END_PROPERTY) ) {
            multicastRangeEnd.setText(ConfigurationModel.getMulticastEndRange());
        }
        if ( name.equals(ConfigurationModel.PDU_MAX_SIZE_PROPERTY) ) {
            pduExpiryTime.setText(ConfigurationModel.getPduMaxSize());
        }
        if ( name.equals(ConfigurationModel.PDU_EXPIRY_TIME_PROPERTY) ) {
            pduExpiryTime.setText(ConfigurationModel.getUndefinedPduExpiryTime());
        }
        if ( name.equals(ConfigurationModel.NODE_ID_PROPERTY) ) {
            nodeId.setText(ConfigurationModel.getNodeId());
        }
        if ( name.equals(ConfigurationModel.ACK_DELAY_UPPER_BOUND_PROPERTY) ) {
            ackDelayUpperBound.setText(ConfigurationModel.getAckDelayUpperBound());
        }
        if ( name.equals(ConfigurationModel.DATA_AND_ADDRESS_PDU_SEND_DELAY) ) {
            broadcastGroup.setText(ConfigurationModel.getDataAndAdressPduSendDelay().toString());
        }
        if ( name.equals(ConfigurationModel.BROADCAST_GROUP_PROPERTY) ) {
            broadcastGroup.setText(ConfigurationModel.getBroadcastGroup().toString());
        }
        if ( name.equals(ConfigurationModel.BROADCAST_PORT_PROPERTY) ) {
            broadcastPort.setText(Short.toString(ConfigurationModel.getBroadcastPort()));
        }
        if ( name.equals(ConfigurationModel.MAXIMUM_WAIT_FOR_RESPONSE_ON_DELAYED_SEND_PROPERTY) ) {
            maximumWaitForResponseOnDelayedSend.setText(Long.toString(ConfigurationModel
                    .getMaximumWaitForResponseOnDelayedSend()));
        }
        if ( name.equals(ConfigurationModel.DEFAULT_TIME_TO_LIVE_PROPERTY) ) {
            defaultTimeToLive.setText(Long.toString(ConfigurationModel.getDefaultTimeToLive()));
        }
        if ( name.equals(ConfigurationModel.WAIT_FOR_IN_USE_RESPONSE_PROPERTY) ) {
            waitForInUseResponse.setText(Long.toString(ConfigurationModel.getWaitForInUseResponse()));
        }
    }

    /**
     * getButtonConfigClear gets the apply button for configurations.
     * 
     * @return clear button from configuration
     */
    public JButton getButtonConfigClear() {
        return buttonConfigClear;
    }

    /**
     * getButtonConfigClear gets the apply button for configurations.
     * 
     * @return apply button from configuration
     */
    public JButton getButtonConfigApply() {
        return buttonConfigApply;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // APPLY CHANGES TO CONFIGURATION
        if ( e.getActionCommand().equals("Apply") && e.getSource() == this.getButtonConfigApply() ) {
            try {
                this.applyChangesConfiguration();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            }
        }

        // CLEAR CHANGES TO CONFIGURATION
        if ( e.getActionCommand().equals("Clear") && e.getSource() == this.getButtonConfigClear() ) {
            this.setModelConfiguration();
        }
    }
}