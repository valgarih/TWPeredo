
//package org.jnetpcap.examples;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.*;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.PcapBpfProgram;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.tcpip.*;
import org.jnetpcap.protocol.network.*;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.lan.IEEE802dot2;
import org.jnetpcap.protocol.lan.IEEE802dot3;


public class ClassicPcapExample {

	/**
	 * Main startup method
	 *
	 * @param args
	 *          ignored
	 */
   private static String asString(final byte[] mac) {
    final StringBuilder buf = new StringBuilder();
    for (byte b : mac) {
      if (buf.length() != 0) {
        buf.append(':');
      }
      if (b >= 0 && b < 16) {
        buf.append('0');
      }
      buf.append(Integer.toHexString((b < 0) ? b + 256 : b).toUpperCase());
    }

    return buf.toString();
  }

	public static void main(String[] args) {
		List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
		StringBuilder errbuf = new StringBuilder(); // For any error msgs

		/***************************************************************************
		 * First get a list of devices on this system
		 **************************************************************************/
		int r = Pcap.findAllDevs(alldevs, errbuf);
		if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
			System.err.printf("Can't read list of devices, error is %s", errbuf
			    .toString());
			return;
		}

		System.out.println("Network devices found:");

		int i = 0;
                try{
		for (PcapIf device : alldevs) {
			String description =
			    (device.getDescription() != null) ? device.getDescription()
			        : "No description available";
                        final byte[] mac = device.getHardwareAddress();
			String dir_mac = (mac==null)?"No tiene direccion MAC":asString(mac);
                        System.out.printf("#%d: %s [%s] MAC:[%s]\n", i++, device.getName(), description, dir_mac);

		}//for

		PcapIf device = alldevs.get(0); // We know we have atleast 1 device
		System.out
		    .printf("\nChoosing '%s' on your behalf:\n",
		        (device.getDescription() != null) ? device.getDescription()
		            : device.getName());

		/***************************************************************************
		 * Second we open up the selected device
		 **************************************************************************/
                /*"snaplen" is short for 'snapshot length', as it refers to the amount of actual data captured from each packet passing through the specified network interface.
                64*1024 = 65536 bytes; campo len en Ethernet(16 bits) tam máx de trama */

		int snaplen = 64 * 1024;           // Capture all packets, no trucation
		int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
		int timeout = 10 * 1000;           // 10 seconds in millis
                Pcap pcap =
		    Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

		if (pcap == null) {
			System.err.printf("Error while opening device for capture: "
			    + errbuf.toString());
			return;
		}//if

                       /********F I L T R O********/
            PcapBpfProgram filter = new PcapBpfProgram();
            String expression =""; // "port 80";
            int optimize = 0; // 1 means true, 0 means false
            int netmask = 0;
            int r2 = pcap.compile(filter, expression, optimize, netmask);
            if (r2 != Pcap.OK) {
                System.out.println("Filter error: " + pcap.getErr());
            }//if
            pcap.setFilter(filter);
                /****************/

                
		/***************************************************************************
		 * Third we create a packet handler which will receive packets from the
		 * libpcap loop.
		 **********************************************************************/
		PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {

			public void nextPacket(PcapPacket packet, String user) {

				System.out.printf("Received packet at %s caplen=%-4d len=%-4d %s\n",
				    new Date(packet.getCaptureHeader().timestampInMillis()),
				    packet.getCaptureHeader().caplen(),  // Length actually captured
				    packet.getCaptureHeader().wirelen(), // Original length
				    user                                 // User supplied object
				    );
                                /******Desencapsulado********/
                                System.out.printf("****primer byte del paquete: %X",packet.getUShort(0));
                                System.out.println("Encabezado: "+ packet.toHexdump());
                                IEEE802dot3 i3e = new IEEE802dot3();
                                if(packet.hasHeader(i3e)){
                                    int longitud = i3e.getUShort(13);
                                    System.out.printf("Longitud:::%X",longitud);
                                }//ieee802dot3
                                Ethernet eth = new Ethernet();
                                if(packet.hasHeader(eth)){
                                    int longitud = eth.getUShort(12);
                                    System.out.printf("Longitud:::%X",longitud);
                                    //JBuffer buffer = eth;
                                    int tipo = eth.type();
                                    //System.out.println("Tipo:"+tipo);
                                    System.out.printf("Tipo=%X\n", tipo);
                                    switch(tipo){
                                        case (int)1:
                                            System.out.println("Trama LLC individual");
                                            IEEE802dot2 llc = new IEEE802dot2();
                                            if(packet.hasHeader(llc)){
                                                int ssap = llc.ssap();
                                                int dsap = llc.dsap();
                                                int control = llc.control();
                                                System.out.printf("SSAP: %s", ssap);
                                                System.out.printf("DSAP: %s", dsap);
                                                System.out.printf("Control: %x", control);
                                            }//if
                                            break;
                                        case (int)2:
                                            System.out.println("Trama LLC de grupo");
                                            IEEE802dot2 llcg = new IEEE802dot2();
                                            if(packet.hasHeader(llcg)){
                                                int ssap = llcg.ssap();
                                                int dsap = llcg.dsap();
                                                int control = llcg.control();
                                                System.out.printf("SSAP: %s", ssap);
                                                System.out.printf("DSAP: %s", dsap);
                                                System.out.printf("Control: %x", control);
                                            }//if
                                            break;
                                        case (int)2054:
                                            System.out.println("Mensaje ARP");
                                            Arp arp = new Arp();
                                            if(packet.hasHeader(arp)){
                                            int operacion = arp.operation();
                                            int[] sp= new int[4];
                                            int[] tp= new int[4];
                                            sp[0] = ((arp.spa()[0])<0)?(arp.spa()[0])+256:arp.spa()[0];
                                            sp[1] = ((arp.spa()[1])<0)?(arp.spa()[1])+256:arp.spa()[1];
                                            sp[2] = ((arp.spa()[2])<0)?(arp.spa()[2])+256:arp.spa()[2];
                                            sp[3] = ((arp.spa()[3])<0)?(arp.spa()[3])+256:arp.spa()[3];
                                            tp[0] = ((arp.tpa()[0])<0)?(arp.tpa()[0])+256:arp.tpa()[0];
                                            tp[1] = ((arp.tpa()[1])<0)?(arp.tpa()[1])+256:arp.tpa()[1];
                                            tp[2] = ((arp.tpa()[2])<0)?(arp.tpa()[2])+256:arp.tpa()[2];
                                            tp[3] = ((arp.tpa()[3])<0)?(arp.tpa()[3])+256:arp.tpa()[3];
                                            if(operacion==1){
				                 if (sp.equals(tp)) {
                                                    System.out.println("ARP gratuito direccion " + sp[0]+"."+sp[1]+"."+sp[2]+"."+sp[3]);
                                                } else {
                                                    System.out.println("Consulta ARP Quien tiene la direccion " + tp[0]+"."+tp[1]+"."+tp[2]+"."+tp[3] + "??");
                                                }//else
                                            } else if (operacion == 2) {
                                                System.out.println("Respuesta ARP " + sp[0]+"."+sp[1]+"."+sp[2]+"."+sp[3] + " es" + asString(arp.sha()));
                                            }//if
                                            }//if
                                            break;
                                        case (int)2048:
                                            System.out.println("Paquete IP");
                                            Ip4 ip = new Ip4();
                                            if (packet.hasHeader(ip)) {
                                                int s1 = ((ip.source()[0])<0)?(ip.source()[0])+256:ip.source()[0];
                                                int s2 = ((ip.source()[1])<0)?(ip.source()[1])+256:ip.source()[1];
                                                int s3 = ((ip.source()[2])<0)?(ip.source()[2])+256:ip.source()[2];
                                                int s4 = ((ip.source()[3])<0)?(ip.source()[3])+256:ip.source()[3];
                                                int d1 = ((ip.destination()[0])<0)?(ip.destination()[0])+256:ip.destination()[0];
                                                int d2 = ((ip.destination()[1])<0)?(ip.destination()[1])+256:ip.destination()[1];
                                                int d3 = ((ip.destination()[2])<0)?(ip.destination()[2])+256:ip.destination()[2];
                                                int d4 = ((ip.destination()[3])<0)?(ip.destination()[3])+256:ip.destination()[3];

                                                System.out.println("IP destino: "+d1+"."+d2+"."+d3+"."+d4);
                                                System.out.println("IP origen: "+s1+"."+s2+"."+s3+"."+s4);
                                                int protocolo = ip.type();
                                                System.out.println("Protocolo"+protocolo+" Descripcion:"+ip.typeDescription());
                                                //System.out.println("Protocolo_d"+ip.typeDescription());
                                                switch(protocolo){
                                                    case 6:
                                                        Tcp tcp = new Tcp();
                                                        if (packet.hasHeader(tcp)) {
                                                            System.out.println("Puerto origen:"+tcp.source()+"    Puerto destino:"+tcp.destination());
                                                            //Payload p = new Payload();
                                                            
                                                            byte[] data = packet.getByteArray(0, packet.size());
                                                            System.out.println("Datos:");
                                                            BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data)));
                                                            String linea="";
                                                            try{
                                                            while((linea= br.readLine())!=null){
                                                                System.out.println(linea);
                                                            }//while
                                                            br.close();
                                                           System.out.println("\n\n");
                                                            }catch(IOException e){e.printStackTrace();}
                                                        }//if

                                                        break;
                                                    default:
                                                }//switch_protocolo                                                
                                            }//if_ip
                                            break;
                                        default:
                                    }//switch

                                }//if
                                /***************/
			}
		};

         
		/***************************************************************************
		 * Fourth we enter the loop and tell it to capture 10 packets. The loop
		 * method does a mapping of pcap.datalink() DLT value to JProtocol ID, which
		 * is needed by JScanner. The scanner scans the packet buffer and decodes
		 * the headers. The mapping is done automatically, although a variation on
		 * the loop method exists that allows the programmer to sepecify exactly
		 * which protocol ID to use as the data link type for this pcap interface.
		 **************************************************************************/
		pcap.loop(10, jpacketHandler, "jNetPcap rocks!");

		/***************************************************************************
		 * Last thing to do is close the pcap handle
		 **************************************************************************/
		pcap.close();
                }catch(IOException e){e.printStackTrace();}
	}
}
