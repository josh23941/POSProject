<%-- 
    Document   : Menu
    Created on : Mar 29, 2013, 1:23:14 PM
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <!--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>-->
        <script type="text/javascript" src="/POSProject/resources/gettheme.js"></script>
        <link rel="stylesheet" href="/POSProject/resources/jqx.base.css" type="text/css" />
        <script type="text/javascript" src="/POSProject/resources/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="/POSProject/resources/jqxcore.js"></script>
        <script type="text/javascript" src="/POSProject/resources/jqxlistmenu.js"></script>
        <script type="text/javascript" src="/POSProject/resources/jqxbuttons.js"></script>
        <script type="text/javascript">
           $(document).ready(function(){
                console.log("inside ready function");
                $('#list').jqxListMenu({width:'50%',
                                        enableScrolling: false,
                                        theme: getDemoTheme(),
                                        showHeader: true,
                                        showBackButton: true,
                                        showFilter: false});
            });
            
            //sends off update to order in DB...for now this is set up to test one order with uid = 0.
            //also adds to the visible representation of the current order on screen.
            function addItemToOrder(name){
                var request = new XMLHttpRequest();
                var paramString = "itemName=" + name + "&uid=1";
                request.open("POST", "update_order", true);
                request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                request.setRequestHeader("Content-length", paramString.length);
                request.setRequestHeader("Connection", "close");
                request.send(paramString);
                //add code for building the visual order representation which is separate from the actual DB entry.
            }
            
        </script>
    </head>
    <body class="default">
        <ul id="list" data-role="listmenu">
           <c:out value="${htmlMenu}" escapeXml="false"/>
        </ul>   
           <!-- 
           <ul id="list" data-role="listmenu">
        <li>OSI
            <ul data-role="listmenu">
                <li>Application Layer
                    <ol data-role="listmenu">
                        <li>SIP</li>
                        <li>DNS</li>
                        <li>FTP</li>
                        <li>RTP</li>
                        <li>DHCP</li>
                    </ol>
                </li>
                <li>Presentation Layer
                    <ol data-role="listmenu">
                        <li>SSL</li>
                        <li>TLS</li>
                    </ol>
                </li>
                <li>Session Layer
                    <ol data-role="listmenu">
                        <li>NetBIOS</li>
                        <li>SPDY</li>
                    </ol>
                </li>
                <li>Transport Layer
                    <ol data-role="listmenu">
                        <li>TCP</li>
                        <li>UDP</li>
                        <li>SCTP</li>
                    </ol>
                </li>
                <li>Network Layer
                    <ol data-role="listmenu">
                        <li>IP</li>
                        <li>ARP</li>
                        <li>ICMP</li>
                    </ol>
                </li>
                <li>Data Link Layer
                    <ol data-role="listmenu">
                        <li>ATM</li>
                        <li>SDLS</li>
                        <li>LLC</li>
                    </ol>
                </li>
                <li>Physical Layer
                    <ol data-role="listmenu">
                        <li>EIA/TIA-232</li>
                        <li>ITU-T V-Series</li>
                        <li>DSL</li>
                    </ol>
                </li>
            </ul>
        </li>
        <li>TCP/IP
            <ul data-role="listmenu">
                <li>Application layer
                    <ol data-role="listmenu">
                        <li>DHCP</li>
                        <li>DNS</li>
                        <li>FTP</li>
                        <li>HTTP</li>
                        <li>IMAP</li>
                        <li>LDAP</li>
                        <li>XMPP</li>
                        <li>SSH</li>
                        <li>RIP</li>
                    </ol>
                </li>
                <li>Transport layer
                    <ol data-role="listmenu">
                        <li>TCP</li>
                        <li>UDP</li>
                        <li>SCTP</li>
                    </ol>
                </li>
                <li>Internet layer
                    <ol data-role="listmenu">
                        <li>IP</li>
                        <li>ICMP</li>
                        <li>ECN</li>
                    </ol>
                </li>
                <li>Link layer
                    <ol data-role="listmenu">
                        <li>ARP</li>
                        <li>NDP</li>
                        <li>DSL</li>
                    </ol>
                </li>
            </ul>
        </li>
    </ul>
        -->
    </body>
</html>
