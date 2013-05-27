package no.ntnu.acp142.configui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.InetAddress;
import java.net.UnknownHostException;

import no.ntnu.acp142.Configuration;

/*
 * Copyright (c) 2013, Luka Cetusic, Thomas Martin Schmid, Karl Mardoff
 * Kittilsen
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
 * ConfigurationModel stores all the data that the configuration view needs.
 * Every change made in configView will make the model get updated and then it
 * will make the configView to update its view for the user.
 * 
 * @author Luka Cetusic, Thomas Martin Schmid, Karl Mardoff Kittilsen
 */
public class ConfigurationModel {

    // -----------------------------------
    // PropertyChangeSupport
    // -----------------------------------

    private static PropertyChangeSupport propertyChangeSupport;

    public static final String           BROADCAST_GROUP_PROPERTY                           = "BroadcastGroup";
    public static final String           BROADCAST_PORT_PROPERTY                            = "BroadcastPort";
    public static final String           MAXIMUM_WAIT_FOR_RESPONSE_ON_DELAYED_SEND_PROPERTY = "MaximumWaitForResponseOnDelayedSend";
    public static final String           USE_DYNAMIC_MULTICAST_PROPERTY                     = "UseDynamicMulticast";
    public static final String           USE_PERSISTANT_MULTICAST_PROPERTY                  = "UsePersistantMulticast";
    public static final String           DEFAULT_TIME_TO_LIVE_PROPERTY                      = "DefaultTimeToLive";
    public static final String           WAIT_FOR_IN_USE_RESPONSE_PROPERTY                  = "WaitForInUseResponse";

    public static final String           WAIT_FOR_REJECT_TIME_PROPERTY                      = "WaitForRejectTime";
    public static final String           ANNOUNCE_DELAY_PROPERTY                            = "AnnounceDelay";
    public static final String           ANNOUNCE_CT_PROPERTY                               = "AnnounceCt";
    public static final String           ACK_RETRANSMISSION_TIME_PROPERTY                   = "AckRetransmissionT";
    public static final String           BACKOFF_FACTOR_PROPERTY                            = "BackoffFactor";
    public static final String           EMCON_RTC_PROPERTY                                 = "EmconRtc";
    public static final String           EMCON_RTI_PROPERTY                                 = "EmconRti";
    public static final String           MM_PROPERTY                                        = "Mm";
    public static final String           ACK_PDU_TIME_PROPERTY                              = "AckPduTime";

    public static final String           GG_PROPERTY                                        = "Gg";
    public static final String           T_PORT_PROPERTY                                    = "TPort";
    public static final String           R_PORT_PROPERTY                                    = "RPort";
    public static final String           D_PORT_PROPERTY                                    = "DPort";
    public static final String           A_PORT_PROPERTY                                    = "APort";
    public static final String           MULTICAST_RANGE_START_PROPERTY                     = "MulticastRangeStart";
    public static final String           MULTICAST_RANGE_END_PROPERTY                       = "MulticastRangeEnd";

    public static final String           PDU_MAX_SIZE_PROPERTY                              = "PduMaxSize";
    public static final String           PDU_EXPIRY_TIME_PROPERTY                           = "PduExpiryTime";
    public static final String           NODE_ID_PROPERTY                                   = "NodeId";
    public static final String           ACK_DELAY_UPPER_BOUND_PROPERTY                     = "AckDelayUpperBound";
    public static final String           DATA_AND_ADDRESS_PDU_SEND_DELAY                    = "DataAndAddressPduSendDelay";

    // -----------------------------------
    // Chat Application-specific settings
    // -----------------------------------

    /**
     * Maximum length of message history for the active chat
     */
    private static int                   maximumMessagesToKeepForActiveChat                 = 500;
    /**
     * Maximum length of message history for the active chat
     */
    private static int                   maximumMessagesToKeepForInactiveChat               = 100;
    /**
     * Multicast group to used to share lists of the users in coordinationGroup.
     */
    private static String                broadcastGroup                                     = "239.1.1.117";
    /**
     * Port to listen to broadcastGroup on.
     */
    private static short                 broadcastPort                                      = 27812;
    /**
     * When we wait for responses before sending (see
     * Networking.delayedConditionalSend), we wait a random amount of time, from
     * 0 to this amount of milliseconds.
     */
    private static long                  maximumWaitForResponseOnDelayedSend                = 1000;
    /**
     * When set to true, topics can be dynamically created and deleted. If set
     * to false, the topics are set up on application startup from a table and
     * are immutable.
     */
    private static boolean               useDynamicTopics                                   = true;
    /**
     * Whether to use dynamic multicast groups in libjpmul transmissions.
     */
    private static boolean               useDynamicMulticast                                = true;
    /**
     * Whether to use persistant groups with dynamic multicast.
     */
    private static boolean               usePersistantGroups                                = true;
    /**
     * Default time to live of ACP142 messages
     */
    private static long                  defaultTimeToLive                                  = 60;
    /**
     * Time to wait for a response before deleting a topic.
     */
    private static long                  waitForInUseResponse                               = 20;

    /**
     * Constructor that initializes propertyChangeSupport
     */
    public ConfigurationModel() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Adds the propertyChangeListener to our propertyChangeSupport object
     * 
     * @param listener
     *            to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    // -------------------------- GETTERS --------------------------------------
    // //

    /**
     * Gets the multicast group used to coordinate IDs of nodes listening to
     * coordinationGroup
     * 
     * @return Address of multicast group
     */
    public static InetAddress getBroadcastGroup() {
        try {
            return InetAddress.getByName(broadcastGroup);
        } catch (UnknownHostException e) {
            // Since we test for exceptions when setting the field, this can
            // only happen for the default group
            System.out
                    .println("Error in ConfigurationModel.getBroadcastGroup(): Unknown host attempted gotten. Returning null!");
            return null;
        }
    }

    /**
     * Gets the port used by broadcast group.
     * 
     * @return Port to broadcast to.
     */
    public static short getBroadcastPort() {
        return broadcastPort;
    }

    /**
     * If true, topics are mutable to the extent of creation and deletion. If
     * false, all topics must be defined on load, and be set in
     * DefaultTopicList. This setting is hardcoded.
     * 
     * @return true if topics are mutable.
     */
    public static boolean useDynamicTopics() {
        return useDynamicTopics;
    }

    /**
     * Determines whether to use dynamic multicast groups when transmitting over
     * ACP142.
     * 
     * @return true if we should use dynamic groups.
     */
    public static boolean useDynamicMulticast() {
        return useDynamicMulticast;
    }

    /**
     * Determines whether to use persistant multicast groups with dynamic
     * multicast when transmitting over ACP142.
     * 
     * @return true if we should use persistant groups.
     */
    public static boolean usePersistantGroups() {
        return usePersistantGroups;
    }

    /**
     * Gets the default time to live for ACP142 messages.
     * 
     * @return default expiry time.
     */
    public static long getDefaultTimeToLive() {
        return defaultTimeToLive;
    }

    /**
     * Gets the time to wait for a response when querying the deletion of a
     * topic.
     * 
     * @return time to wait.
     */
    public static long getWaitForInUseResponse() {
        return waitForInUseResponse;
    }

    /**
     * Gets the maximum time to wait for a response in delayedConditionalSend.
     * 
     * @return Maximum time to wait.
     */
    public static long getMaximumWaitForResponseOnDelayedSend() {
        return maximumWaitForResponseOnDelayedSend;
    }

    /**
     * Time between sending a Request_PDU and an affiliated Announce_PDU.
     * 
     * @return wait for reject time
     */
    public static String getWaitForRejectTime() {
        return Integer.toString(Configuration.getWaitForRejectTime());
    }

    /**
     * Time between sending an Announce_PDU and the first affiliated
     * Address_PDU.
     * 
     * @return announce delay
     */
    public static String getAnnounceDelay() {
        return Integer.toString(Configuration.getAnnounceDelay());
    }

    /**
     * The number of times the Announce_PDU is transmitted.
     * 
     * @return announce CT
     */
    public static String getAnnounceCt() {
        return Integer.toString(Configuration.getAnnounceCt());
    }

    /**
     * Time a transmitter waits before re-transmitting a message to receivers
     * not in EMCON, if no acknowledgment has been received.
     * 
     * @return ACK re-transmission time in milliseconds.
     */
    public static String getAckRetransmissionTime() {
        return Integer.toString(Configuration.getAckRetransmissionTime());
    }

    /**
     * Multiplying factor applied to ACK_RE-TRANSMISSION_TIME on subsequent
     * re-transmissions, to achieve exponentially increasing delay.
     * 
     * @return back-off factor
     */
    public static String getBackoffFactor() {
        return Double.toString(Configuration.getBackoffFactor());
    }

    /**
     * Re-transmission count - Maximum number of message re-transmission for
     * receivers in EMCON.
     * 
     * @return EMCON RTC
     */
    public static String getEmconRtc() {
        return Integer.toString(Configuration.getEmconRtc());
    }

    /**
     * Re-transmission interval - Time in milliseconds a transmitter waits
     * before re-transmitting a message for receivers in EMCON.
     * 
     * @return EMCON RTI in milliseconds.
     */
    public static String getEmconRti() {
        return Integer.toString(Configuration.getEmconRti());
    }

    /**
     * Maximum number of new entries in the list of Missing_Data_PDU_Seq_Numbers
     * field in an Ack_Info_Entry of an Ack_PDU.
     * 
     * @return MM
     */
    public static String getMm() {
        return Integer.toString(Configuration.getMm());
    }

    /**
     * Time a receiver waits before re-transmitting Ack_PDU(s) if no response is
     * received from the transmitting node.
     * 
     * @return ACK PDU time in milliseconds.
     */
    public static String getAckPduTime() {
        return Integer.toString(Configuration.getAckPduTime());
    }

    /**
     * Multicast group address to which all nodes should join in order to allow
     * dynamic building of multicast groups.
     * 
     * @return GG
     */
    public static String getGg() {
        try {
            return Configuration.getGg().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Port number used for the transmission of Request_PDUs, Reject_PDUs and
     * Release_PDUs between the transmission programs. All transmitter processes
     * have to listen to this port in conjunction with the multicast group GG.
     * 
     * @return TPORT
     */
    public static String getTPort() {
        return Integer.toString(Configuration.getTPort());
    }

    /**
     * Port number used by the transmitters to send the Announce_PDUs, informing
     * those receivers involved in the concerning message transfer to join a
     * specific multicast group. All receiver processes have to listen to this
     * port in conjunction with the multicast group GG.
     * 
     * @return TPORT
     */
    public static String getRPort() {
        return Integer.toString(Configuration.getRPort());
    }

    /**
     * Port used for the data traffic from the Message Transmitter of
     * Multicast_OUT to the Message Receiver of Multicast_IN.
     * 
     * @return DPORT
     */
    public static String getDPort() {
        return Integer.toString(Configuration.getDPort());
    }

    /**
     * Port used for the traffic from the Message Receiver of Multicast_IN to
     * the message Transmitter of Multicast_OUT.
     * 
     * @return APORT
     */
    public static String getAPort() {
        return Integer.toString(Configuration.getAPort());
    }

    /**
     * Returns the first address in the pool we use to assign dynamic multicast
     * groups.
     * 
     * @return The starting position.
     */
    public static String getMulticastStartRange() {
        try {
            return Configuration.getMulticastStartRange().getHostAddress();
        } catch (UnknownHostException e) {
            return "Unknown host!";
        }
    }

    /**
     * Returns the last address in the pool we use to assign dynamic multicast
     * groups.
     */
    public static String getMulticastEndRange() {
        try {
            return Configuration.getMulticastEndRang().getHostAddress();
        } catch (UnknownHostException e) {
            return "Unknown host!";
        }
    }

    /**
     * Returns the maximum size of a PDU. <br>
     * 
     * @return Maximum size of a PDU
     */
    public static String getPduMaxSize() {
        return Integer.toString(Configuration.getPduMaxSize());
    }

    /**
     * Returns the default timeout value of a PDU in milliseconds.
     * 
     * @return Default Expiry time of a PDU in milliseconds.
     */
    public static String getUndefinedPduExpiryTime() {
        return Integer.toString(Configuration.getUndefinedPduExpiryTime());
    }

    /**
     * Returns the ID of this node
     * 
     * @return The ID of this node
     */
    public static String getNodeId() {
        return Integer.toString(Configuration.getNodeId());
    }

    /**
     * Returns the upper bound of how long we wait before sending an ack PDU.
     * 
     * @return the upper bound of how long we wait before sending an ack PDU
     */
    public static String getAckDelayUpperBound() {
        return Integer.toString((int) Configuration.getAckDelayUpperBound());
    }

    /**
     * Gets the maximum message count to keep in the Chat object of the
     * currently active chat.
     * 
     * @return maximum length of chat history.
     */
    public static int getMaximumMessagesToKeepForActiveChat() {
        return maximumMessagesToKeepForActiveChat;
    }

    /**
     * Gets the maximum message count to keep in the Chat object of the
     * currently inactive chats.
     * 
     * @return maximum length of chat history.
     */
    public static int getMaximumMessagesToKeepForInactiveChat() {
        return maximumMessagesToKeepForInactiveChat;
    }

    /**
     * Get the delay that we use between sending the address PDU and data PDU,
     * as well as between each data PDU.
     * 
     * @return dataAndAddressPduSendDelay in milliseconds.
     */
    public static String getDataAndAdressPduSendDelay() {
        return Integer.toString((int) Configuration.getDataAndAddressPduSendDelay());
    }

    // -------------------------- SETTERS --------------------------------------
    // //

    /**
     * Sets the maximum message count to keep in the Chat object of the
     * currently inactive chats.
     * 
     * @param maximumMessagesToKeepForActiveChat
     *            maximum length of chat history.
     */
    public static void setMaximumMessagesToKeepForActiveChat(int maximumMessagesToKeepForActiveChat) {
        ConfigurationModel.maximumMessagesToKeepForActiveChat = maximumMessagesToKeepForActiveChat;
    }

    /**
     * Sets the maximum message count to keep in the Chat object of the
     * currently inactive chats.
     * 
     * @param maximumMessagesToKeepForInactiveChat
     *            maximum length of chat history.
     */
    public static void setMaximumMessagesToKeepForInactiveChat(int maximumMessagesToKeepForInactiveChat) {
        ConfigurationModel.maximumMessagesToKeepForInactiveChat = maximumMessagesToKeepForInactiveChat;
    }

    /**
     * Sets the multicast group used to coordinate IDs of nodes listening to
     * coordinationGroup. Tests the address so exceptions are thrown here, and
     * not when setup is attempted at a later time.
     * 
     * @param hostname
     *            of address of multicast group
     */
    public static void setBroadcastGroup(String hostname) {
        try {
            InetAddress.getByName(hostname);
            broadcastGroup = hostname;
        } catch (UnknownHostException e) {
            System.out.println("Error in ConfigurationModel.setBroadcastGroup(): Unknown host'" + hostname
                    + "', old hostname maintained.");
        }
    }

    /**
     * Sets the port used by broadcast group.
     * 
     * @param broadcastPort
     *            to use.
     */
    public static void setBroadcastPort(short broadcastPort) {
        String oldValue = Short.toString(getBroadcastPort());
        ConfigurationModel.broadcastPort = broadcastPort;
        propertyChangeSupport.firePropertyChange(BROADCAST_PORT_PROPERTY, oldValue, broadcastPort);
    }

    /**
     * Sets whether to use dynamic multicast groups when transmitting over
     * libjpmul.
     * 
     * @param useDynamic
     *            true if we are to use dynamic groups.
     */
    public static void setUseDynamicMulticast(boolean useDynamic) {
        String oldValue = Boolean.toString(useDynamicMulticast());
        useDynamicMulticast = useDynamic;
        propertyChangeSupport.firePropertyChange(USE_DYNAMIC_MULTICAST_PROPERTY, oldValue, useDynamic);
    }

    /**
     * Determines whether to use persistant multicast groups with dynamic
     * multicast when transmitting over ACP142.
     * 
     * @param usePersistant
     *            true if we should use persistant groups.
     */
    public static void setUsePersistantGroups(boolean usePersistant) {
        String oldValue = Boolean.toString(usePersistantGroups());
        usePersistantGroups = usePersistant;
        propertyChangeSupport.firePropertyChange(USE_PERSISTANT_MULTICAST_PROPERTY, oldValue, usePersistant);
    }

    /**
     * Sets whether to use dynamic topics or not. This should be set on startup
     * and not be changed afterwards! Dynamic topics mean we allow the users to
     * create and delete topics. Any topic set in the topic list file are not
     * deletable.
     * 
     * @param useDynamic
     *            true if we are to use dynamic topics.
     */
    public static void setUseDynamicTopics(boolean useDynamic) {
        String oldValue = Boolean.toString(useDynamicTopics());
        useDynamicTopics = useDynamic;
        propertyChangeSupport.firePropertyChange(USE_DYNAMIC_MULTICAST_PROPERTY, oldValue, useDynamic);
    }

    /**
     * Sets the default time to live for ACP142 messages.
     * 
     * @param time
     *            before expiry.
     */
    public static void setDefaultTimeToLive(long time) {
        String oldValue = Long.toString(getDefaultTimeToLive());
        defaultTimeToLive = time;
        propertyChangeSupport.firePropertyChange(DEFAULT_TIME_TO_LIVE_PROPERTY, oldValue, time);
    }

    /**
     * Sets the time to wait for a response when querying the deletion of a
     * topic, before deleting it.
     * 
     * @param wait
     *            Time to wait.
     */
    public static void setWaitForInUseResponse(long wait) {
        String oldValue = Long.toString(getWaitForInUseResponse());
        waitForInUseResponse = wait;
        propertyChangeSupport.firePropertyChange(WAIT_FOR_IN_USE_RESPONSE_PROPERTY, oldValue, wait);
    }

    /**
     * Sets the maximum time to wait for a response in delayedConditionalSend.
     * 
     * @param wait
     *            Maximum time to wait in milliseconds.
     */

    public static void setMaximumWaitForResponseOnDelayedSend(long wait) {
        String oldValue = Long.toString(getMaximumWaitForResponseOnDelayedSend());
        maximumWaitForResponseOnDelayedSend = wait;
        propertyChangeSupport.firePropertyChange(MAXIMUM_WAIT_FOR_RESPONSE_ON_DELAYED_SEND_PROPERTY, oldValue, wait);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param waitForRejectTime
     *            time to wait for rejectPDUs in milliseconds
     */
    public static void setWaitForRejectTime(int waitForRejectTime) {
        String oldValue = getWaitForRejectTime();
        Configuration.setWaitForRejectTime(waitForRejectTime);
        propertyChangeSupport.firePropertyChange(WAIT_FOR_REJECT_TIME_PROPERTY, oldValue, waitForRejectTime);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param announceDelay
     *            time to wait for after announce before sending in milliseconds
     */
    public static void setAnnounceDelay(int announceDelay) {
        String oldValue = getAnnounceDelay();
        Configuration.setAnnounceDelay(announceDelay);
        propertyChangeSupport.firePropertyChange(ANNOUNCE_DELAY_PROPERTY, oldValue, announceDelay);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param announceCt
     *            Number of announcePDUs to send
     */
    public static void setAnnounceCt(int announceCt) {
        String oldValue = getAnnounceCt();
        Configuration.setAnnounceCt(announceCt);
        propertyChangeSupport.firePropertyChange(ANNOUNCE_CT_PROPERTY, oldValue, announceCt);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param ackRetransmissionTime
     *            time between ack retransmissions in milliseconds
     */
    public static void setAckRetransmissionTime(int ackRetransmissionTime) {
        String oldValue = getAckRetransmissionTime();
        Configuration.setAckRetransmissionTime(ackRetransmissionTime);
        propertyChangeSupport.firePropertyChange(ACK_RETRANSMISSION_TIME_PROPERTY, oldValue, ackRetransmissionTime);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param backoffFactor
     *            Factor by which to back off when retransmitting
     */
    public static void setBackoffFactor(double backoffFactor) {
        String oldValue = getBackoffFactor();
        Configuration.setBackoffFactor(backoffFactor);
        propertyChangeSupport.firePropertyChange(BACKOFF_FACTOR_PROPERTY, oldValue, backoffFactor);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param emconRtc
     *            Number of retransmissions to nodes in emcon mode.
     */
    public static void setEmconRtc(int emconRtc) {
        String oldValue = getEmconRtc();
        Configuration.setEmconRtc(emconRtc);
        propertyChangeSupport.firePropertyChange(EMCON_RTC_PROPERTY, oldValue, emconRtc);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param emconRti
     *            Interval between transmissions to nodes in emcon mode in
     *            milliseconds.
     */
    public static void setEmconRti(int emconRti) {
        String oldValue = getEmconRti();
        Configuration.setEmconRti(emconRti);
        propertyChangeSupport.firePropertyChange(EMCON_RTI_PROPERTY, oldValue, emconRti);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param mm
     *            Maximum number of new entries in the list of
     *            Missing_Data_PDU_Seq_Numbers field in an Ack_Info_Entry of an
     *            Ack_PDU.
     */
    public static void setMm(int mm) {
        String oldValue = getMm();
        Configuration.setMm(mm);
        propertyChangeSupport.firePropertyChange(MM_PROPERTY, oldValue, mm);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param ackPduTime
     *            the time a receiver waits before re-transmitting Ack_PDU(s) if
     *            no response is received from the transmitting node, in
     *            milliseconds.
     */
    public static void setAckPduTime(int ackPduTime) {
        String oldValue = getAckPduTime();
        Configuration.setAckPduTime(ackPduTime);
        propertyChangeSupport.firePropertyChange(ACK_PDU_TIME_PROPERTY, oldValue, ackPduTime);
    }

    /**
     * Sets the GG parameter without writing to disk.
     * 
     * @param gg
     *            The global multicast group
     */
    public static void setGg(String gg) {
        String oldValue = getGg();
        Configuration.setGg(gg);
        propertyChangeSupport.firePropertyChange(GG_PROPERTY, oldValue, gg);
    }

    /**
     * Sets the TPORT parameter without writing to disk.
     * 
     * @param tPort
     *            The port number of TPORT
     */
    public static void setTPort(int tPort) {
        String oldValue = getTPort();
        Configuration.setTPort(tPort);
        propertyChangeSupport.firePropertyChange(T_PORT_PROPERTY, oldValue, tPort);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param rPort
     *            The port number of RPORT
     */
    public static void setRPort(int rPort) {
        String oldValue = getRPort();
        Configuration.setRPort(rPort);
        propertyChangeSupport.firePropertyChange(R_PORT_PROPERTY, oldValue, rPort);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param dPort
     *            The port number of DPORT
     */
    public static void setDPort(int dPort) {
        String oldValue = getDPort();
        Configuration.setDPort(dPort);
        propertyChangeSupport.firePropertyChange(D_PORT_PROPERTY, oldValue, dPort);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param aPort
     *            The port number of APORT
     */
    public static void setAPort(int aPort) {
        String oldValue = getAPort();
        Configuration.setAPort(aPort);
        propertyChangeSupport.firePropertyChange(A_PORT_PROPERTY, oldValue, aPort);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param pduMaxSize
     *            Maximum size of a PDU, in bytes
     */
    public static void setPduMaxSize(int pduMaxSize) {
        String oldValue = getPduMaxSize();
        Configuration.setPduMaxSize(pduMaxSize);
        propertyChangeSupport.firePropertyChange(PDU_MAX_SIZE_PROPERTY, oldValue, pduMaxSize);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param undefinedPduExpiryTime
     *            Default Expiry time of a PDU.
     */
    public static void setUndefinedPduExpiryTime(int undefinedPduExpiryTime) {
        String oldValue = getUndefinedPduExpiryTime();
        Configuration.setUndefinedPduExpiryTime(undefinedPduExpiryTime);
        propertyChangeSupport.firePropertyChange(PDU_EXPIRY_TIME_PROPERTY, oldValue, undefinedPduExpiryTime);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param nodeId
     *            Node ID of this node
     */
    public static void setNodeId(int nodeId) {
        String oldValue = getNodeId();
        Configuration.setNodeId(nodeId);
        propertyChangeSupport.firePropertyChange(NODE_ID_PROPERTY, oldValue, nodeId);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param multicastStartRange
     *            start address of the multicast pool.
     * @throws UnknownHostException
     */
    public static void setMulticastStartRange(String multicastStartRange) throws UnknownHostException {
        String oldValue = getMulticastStartRange();
        Configuration.setMulticastStartRange(multicastStartRange);
        propertyChangeSupport.firePropertyChange(MULTICAST_RANGE_START_PROPERTY, oldValue, multicastStartRange);
    }

    /**
     * Sets parameter without writing to disk.
     * 
     * @param multicastEndRange
     *            start address of the multicast pool.
     * @throws UnknownHostException
     */
    public static void setMulticastEndRange(String multicastEndRange) throws UnknownHostException {
        String oldValue = getMulticastEndRange();
        Configuration.setMulticastEndRange(multicastEndRange);
        propertyChangeSupport.firePropertyChange(MULTICAST_RANGE_END_PROPERTY, oldValue, multicastEndRange);
    }

    /**
     * Set the upper bound on the sleep delay when sending Ack PDUs
     * 
     * @param upperBound
     *            the upper bound.
     */
    public static void setAckDelayUpperBound(long upperBound) {
        String oldValue = getAckDelayUpperBound();
        Configuration.setAckDelayUpperBound(upperBound);
        propertyChangeSupport.firePropertyChange(ACK_DELAY_UPPER_BOUND_PROPERTY, oldValue, upperBound);
    }

    /**
     * Set the delay that we use between sending the address PDU and data PDU,
     * as well as between each data PDU.
     * 
     * @param dataAndAddressPduSendDelay
     *            in milliseconds.
     */
    public static void setDataAndAdressPduSendDelay(int dataAndAddressPduSendDelay) {
        String oldValue = getDataAndAdressPduSendDelay();
        Configuration.setDataAndAddressPduSendDelay(dataAndAddressPduSendDelay);
        propertyChangeSupport.firePropertyChange(DATA_AND_ADDRESS_PDU_SEND_DELAY, oldValue, dataAndAddressPduSendDelay);
    }
}
