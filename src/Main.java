import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

import com.altitude.network.Client;
import com.altitude.network.Listener;
import com.altitude.network.Listener.DataReadyEvent;
import com.altitude.network.Listener.LostClientEvent;
import com.altitude.network.Listener.NewClientEvent;
import com.altitude.network.NetworkException;

import com.altitude.server.comms.ss_xr_marshall.*;
import com.altitude.server.comms.enumerates.*;

import com.altitude.server.entities.*;
import com.altitude.server.entities.generated.*;
import com.altitude.server.jom.*;
import com.altitude.server.jom.filter.*;
import com.altitude.server.jom.list.*;
import com.altitude.server.jom.record.*;


class Main
{   
//
//	public static USOMAlarmLogQualifier getAlarmQualifier(XrState state, int alarm_log_id) throws Exception
//	{
//        ss_cstub stub = new ss_cstub(state);
//		
//        WHERE_ID whereClause = new WHERE_ID();
//        whereClause.len = 2;
//        whereClause.comp = new ID_PAIR[whereClause.len];
//        whereClause.comp[0] = new ID_PAIR();
//        whereClause.comp[0].ent = ENTITY_DEF.SYS_ALARM_LOG;
//        whereClause.comp[0].id = alarm_log_id;
//        whereClause.comp[1] = new ID_PAIR();
//        whereClause.comp[1].ent = ENTITY_DEF.SYS_ALARM_QUALIF;
//        whereClause.comp[1].id = -1;
//        
//        entityFieldList entityFieldListVar = new entityFieldList();
//        entityFieldListVar.nFilters = 0;
//        entityFieldListVar.filter = null;
//        entityFieldListVar.specialField = null;
//        entityFieldListVar.specialFieldLen = 0;
//        entityFieldListVar.ent = ENTITY_DEF.SYS_ALARM_QUALIF;
//        entityFieldListVar.fieldLen = 2;
//        entityFieldListVar.field = new int[entityFieldListVar.fieldLen];
//        entityFieldListVar.field[0] = 2;
//        entityFieldListVar.field[1] = 3;
//        
//        GET_ENTITIES_IN alarm_qualifiers_req = new GET_ENTITIES_IN();
//        alarm_qualifiers_req.flags = 0;
//        alarm_qualifiers_req.nRequests = 1;
//        alarm_qualifiers_req.entityGet = new GET_OPERATION[alarm_qualifiers_req.nRequests];
//        alarm_qualifiers_req.entityGet[0] = new GET_OPERATION();
//        alarm_qualifiers_req.entityGet[0].seeAll = 0;
//        alarm_qualifiers_req.entityGet[0].assignmentsOp = ASSIGNED_ENTITIES_VIEW_OPTION.ASSIGNED_ENTITIES_ONLY;
//        alarm_qualifiers_req.entityGet[0].whereClause = whereClause;
//		    
//        alarm_qualifiers_req.entityGet[0].fieldsLen = 1;
//        alarm_qualifiers_req.entityGet[0].fieldList = new entityFieldList[alarm_qualifiers_req.entityGet[0].fieldsLen];
//        alarm_qualifiers_req.entityGet[0].fieldList[0] = entityFieldListVar;
//
//        VALIDATE_IN credentials = new VALIDATE_IN();
//        credentials.userName = "adm1";
//        credentials.passwd = "\0".getBytes();
//		        
//		SS_GET_ENTITIES_OUT alarm_qualifiers = stub.ssGetEntities_auth(credentials, alarm_qualifiers_req);
//		
//		USOMAlarmLogQualifier alarmQualifierRecord = new USOMAlarmLogQualifier();
//		alarmQualifierRecord.type = SM_SYSTEM_ALARM_QUALIFIER_TYPE.get((int)alarm_qualifiers.entities[0].retEntity[0].entity.returnData[0].val.short_value);
//		alarmQualifierRecord.name = alarm_qualifiers.entities[0].retEntity[0].entity.returnData[1].val.string_value;
//		
//		return alarmQualifierRecord;
//	}
//	
//	public static USOMAlarmLog getAlarmLog(XrState state, int alarm_log_id) throws Exception	
//	{				
//        ss_cstub stub = new ss_cstub(state);
//
//        VALIDATE_IN credentials = new VALIDATE_IN();
//        credentials.userName = "adm1";
//        credentials.passwd = "\0".getBytes();
//        
//        WHERE_ID whereClause = new WHERE_ID();
//        whereClause.len = 1;
//        whereClause.comp = new ID_PAIR[whereClause.len];
//        whereClause.comp[0] = new ID_PAIR();
//        whereClause.comp[0].ent = ENTITY_DEF.SYS_ALARM_LOG;
//        whereClause.comp[0].id = alarm_log_id;
//        
//        entityFieldList entityFieldListVar = new entityFieldList();
//        entityFieldListVar.nFilters = 0;
//        entityFieldListVar.filter = null;
//        entityFieldListVar.specialField = null;
//        entityFieldListVar.specialFieldLen = 0;
//        entityFieldListVar.ent = ENTITY_DEF.SYS_ALARM_LOG;
//        entityFieldListVar.fieldLen = 3;
//        entityFieldListVar.field = new int[entityFieldListVar.fieldLen];
//        entityFieldListVar.field[0] = 1;
//        entityFieldListVar.field[1] = 2;
//        entityFieldListVar.field[2] = 3;
//        
//        GET_ENTITIES_IN alarm_req = new GET_ENTITIES_IN();
//        alarm_req.flags = 0;
//        alarm_req.nRequests = 1;
//        alarm_req.entityGet = new GET_OPERATION[alarm_req.nRequests];
//        alarm_req.entityGet[0] = new GET_OPERATION();
//        alarm_req.entityGet[0].seeAll = 0;
//        alarm_req.entityGet[0].assignmentsOp = ASSIGNED_ENTITIES_VIEW_OPTION.ASSIGNED_ENTITIES_ONLY;
//        alarm_req.entityGet[0].whereClause = whereClause;
//		    
//        alarm_req.entityGet[0].fieldsLen = 1;
//        alarm_req.entityGet[0].fieldList = new entityFieldList[alarm_req.entityGet[0].fieldsLen];
//        alarm_req.entityGet[0].fieldList[0] = entityFieldListVar;
//        
//		SS_GET_ENTITIES_OUT alarmLog = stub.ssGetEntities_auth(credentials, alarm_req);
//
//		USOMAlarmLog alarmLogRecord = new USOMAlarmLog();
//		alarmLogRecord.type = SM_SYSTEM_ALARM_TYPE.get((int)alarmLog.entities[0].retEntity[0].entity.returnData[0].val.short_value);
//		alarmLogRecord.severity = SM_SYSTEM_ALARM_SEVERITY.get((int)alarmLog.entities[0].retEntity[0].entity.returnData[1].val.short_value);
//		alarmLogRecord.moment = alarmLog.entities[0].retEntity[0].entity.returnData[2].val.double_datetime_value;
//		
//		return alarmLogRecord;
//	}
//	
//    public static GET_ENTITIES_IN fillGET_ENTITIES_IN(ENTITY_DEF entity, String name)
//    {
//        WHERE_ID whereClause = new WHERE_ID();
//        whereClause.len = 1;
//        whereClause.comp = new ID_PAIR[1];
//        whereClause.comp[0] = new ID_PAIR();
//        whereClause.comp[0].ent = entity;
//        whereClause.comp[0].id = -1;
//
//        GET_ENTITIES_IN entities_in = new GET_ENTITIES_IN();
//        entities_in.flags = 0;
//        entities_in.nRequests = 1;
//        entities_in.entityGet = new GET_OPERATION[entities_in.nRequests];
//        entities_in.entityGet[0] = new GET_OPERATION();
//        entities_in.entityGet[0].whereClause = whereClause;
//
//        QryNode filter_node = new QryNode();
//        filter_node.tag = "teste";
//        filter_node.cond = new QryNodeCondition();
//        filter_node.cond.data = new QryColumnRestriction();
//        filter_node.cond.data.type = QryRestrictionType.DB_RT_OP;
//        filter_node.cond.data.value = filter_node.cond.data.new value_u(); 
//        filter_node.cond.data.value.op = QryRestrictionOp.ROP_EQ;
//        filter_node.childNodeLen = 2;
//        filter_node.childNode = new QryNode[filter_node.childNodeLen];
//
//        filter_node.childNode[0] = new QryNode();
//        filter_node.childNode[0].childNodeLen = 0;
//        filter_node.childNode[0].cond = new QryNodeCondition();
//        filter_node.childNode[0].cond.data = new QryColumnRestriction();
//        filter_node.childNode[0].cond.data.type = QryRestrictionType.DB_RT_COLUMN;
//        filter_node.childNode[0].cond.data.value = filter_node.childNode[0].cond.data.new value_u(); 
//        filter_node.childNode[0].cond.data.value.column = new QryRestrictionCol();
//        filter_node.childNode[0].cond.data.value.column.entity = entity;
//        filter_node.childNode[0].cond.data.value.column.req_entity = entity;
//        filter_node.childNode[0].cond.data.value.column.field_id = 3;
//
//        filter_node.childNode[1] = new QryNode();
//        filter_node.childNode[1].childNodeLen = 0;
//        filter_node.childNode[1].cond = new QryNodeCondition();
//        filter_node.childNode[1].cond.data = new QryColumnRestriction();
//        filter_node.childNode[1].cond.data.type = QryRestrictionType.DB_RT_CONST;
//        filter_node.childNode[1].cond.data.value = filter_node.childNode[1].cond.data.new value_u();
//        filter_node.childNode[1].cond.data.value.constant = new QryRestrictionConst();
//        filter_node.childNode[1].cond.data.value.constant.type = DB_EASY_TYPE.DBTYPE_CHAR;
//        filter_node.childNode[1].cond.data.value.constant.value = filter_node.childNode[1].cond.data.value.constant.new value_u(); 
//        filter_node.childNode[1].cond.data.value.constant.value.ttext = name;
//
//        ENTITY_FILTER filter = new ENTITY_FILTER();
//        filter.type = FILTER_TYPE.FILTER_GENERIC;
//        filter.data = filter.new data_u();
//        filter.data.filter_generic = new QryGeneric();
//        filter.data.filter_generic.n_group_by = 0;
//        filter.data.filter_generic.n_order_by = 0;
//        filter.data.filter_generic.n_nodes = 1;
//        filter.data.filter_generic.node = new QryNode[filter.data.filter_generic.n_nodes];
//
//        filter.data.filter_generic.node[0] = filter_node;
//
//        entityFieldList entityFieldListVar = new entityFieldList();
//        entityFieldListVar.nFilters = 1;
//        entityFieldListVar.filter = new ENTITY_FILTER[entityFieldListVar.nFilters];
//        entityFieldListVar.filter[0] = filter;
//        entityFieldListVar.specialField = null;
//        entityFieldListVar.specialFieldLen = 0;
//        entityFieldListVar.ent = entity;
//        entityFieldListVar.fieldLen = 2;
//        entityFieldListVar.field = new int[entityFieldListVar.fieldLen];
//        entityFieldListVar.field[0] = 0;
//        entityFieldListVar.field[1] = 3;
//
//        entities_in.entityGet[0].fieldsLen = 1;
//        entities_in.entityGet[0].fieldList = new entityFieldList[entities_in.entityGet[0].fieldsLen];
//        entities_in.entityGet[0].fieldList[0] = entityFieldListVar;
//
//        return entities_in;
//    }
//
//    public static SS_GET_ENTITIES_OUT ssGetEntities_auth(int port, VALIDATE_IN credentials, GET_ENTITIES_IN arg) throws Exception
//    {
//		XrState state = new XrState();
//		state.channel = SocketChannel.open();
//		state.channel.connect(new InetSocketAddress("wklmr4", port));
//				
//		ss_xr xr_obj = new ss_xr(state);
//	
//        xr_obj.xr_start(XR_OP.XR_OUT);
//        	xr_obj.xr_proc(XR_OP.XR_OUT, -3);        
//        	xr_obj.xr_VALIDATE_IN(XR_OP.XR_OUT, credentials);        
//		xr_obj.xr_end(XR_OP.XR_OUT);
//		
//		xr_obj.xr_start(XR_OP.XR_IN);
//			AUTH_OUT auth = new AUTH_OUT();
//			xr_obj.xr_AUTH_OUT(XR_OP.XR_IN, auth);
//        xr_obj.xr_end(XR_OP.XR_IN);    
//		
//        xr_obj.xr_start(XR_OP.XR_OUT);
//        	xr_obj.xr_proc(XR_OP.XR_OUT, 33);		
//        	xr_obj.xr_GET_ENTITIES_IN(XR_OP.XR_OUT, arg);		
//		xr_obj.xr_end(XR_OP.XR_OUT);
//		
//		/// and receive the reply
//        xr_obj.xr_start(XR_OP.XR_IN);
//        	SS_GET_ENTITIES_OUT output = new SS_GET_ENTITIES_OUT();
//        	output = xr_obj.xr_SS_GET_ENTITIES_OUT(XR_OP.XR_IN, output);
//        xr_obj.xr_end(XR_OP.XR_IN);
//        
//        return output;
//    }
//    
    
	public static class DataReady implements DataReadyEvent
	{
		DataReady()
		{
			
		}
		
		@Override
		public void invoke(Client client) throws NetworkException 
		{
			// TODO Auto-generated method stub
			
		}
	}
	
	public static class NewClient implements NewClientEvent
	{
		NewClient()
		{
		}
		
		public void invoke(Client newClient)
		{
		}
	}
	

	public static class LostClient implements LostClientEvent
	{
		LostClient()
		{
		}
		
		public void invoke(Client lostClient)
		{
		}
	}
	
	public static void getBusinessMonitorChartData(JOMInstance instance, 
			JOMBusinessPanel business_panel, 
			JOMBusinessMonitor business_monitor)
	{
		System.out.print("code: " + business_monitor.getBusinessMonitorCode() + 
						 ", name: " + business_monitor.getBusinessMonitorName() + "\n");

		// start the build of a request
		EntitiesRequest request = new EntitiesRequest();

		int cubeId = business_panel.getBusinessPanelCube();
		JOMCube cube = (JOMCube) instance.InstanceListOfCube().lookup(cubeId);
		JOMCubeDimensionList cubeDimensionList = cube.CubeListOfCubeDimension();
		
		cubeDimensionList.open();
		JOMRecord[] cubeDimensions = cubeDimensionList.get(-1);
		cubeDimensionList.close();
		
		for(JOMRecord cube_dimension_record : cubeDimensions)
		{
			JOMCubeDimension cube_dimension = (JOMCubeDimension) cube_dimension_record;
		
//			cube_dimension.
		}
		
		CommsBUSINESS_MONITOR_TIMEFRAME_ENUM business_monitor_timeframe = CommsBUSINESS_MONITOR_TIMEFRAME_ENUM.get(business_monitor.getBusinessMonitorTimeframe());
		
		// go through the business monitor filter
		// and build a request
		for (GenericFilterColumn column : business_monitor.getBusinessMonitorFilter().columns)
		{
			if (column.field_id == -1)
			{
				
			}
			else
			{
			}
		}
		
	}
	
	public static void main(String [ ] args) throws Exception
	{
		JOMInstance instance = new JOMInstance();
		
		ServerSocketChannel serverSocketChannel = null;
		try 
		{
			serverSocketChannel = ServerSocketChannel.open();
		} 
		catch (IOException e) {}
		
		try 
		{
			serverSocketChannel.socket().bind(new InetSocketAddress(1234));
		} 
		catch (IOException e) {}
        		
		DataReady dataReadyHandler = new DataReady();
		NewClient newClientHandler = new NewClient();
		LostClient lostClientHandler = new LostClient();
		
		Listener mListener = new Listener(serverSocketChannel, newClientHandler, lostClientHandler, dataReadyHandler);
		mListener.start();
		
		instance.connect("wklmr4", "60153");
		if (!instance.login("adm1", "\0", "easy_serv_7"))
			return;
		
		JOMList list = instance.InstanceListOfBusinessPanel();
		list.open();
		JOMRecord[] records = list.get(-1);
		list.close();
		
		for(JOMRecord record : records)
		{
			JOMBusinessPanel business_panel = (JOMBusinessPanel) record;
			JOMList businessMonitorList = business_panel.BusinessPanelListOfBusinessMonitor();
			
			businessMonitorList.open();
			JOMRecord[] business_monitors = businessMonitorList.get(-1);
			businessMonitorList.close();
			
			System.out.print("code: " + business_panel.getBusinessPanelCode() + ", name: " + business_panel.getBusinessPanelName() + "\n");
			
			for(JOMRecord business_monitor_record : business_monitors)
			{
				JOMBusinessMonitor business_monitor = (JOMBusinessMonitor) business_monitor_record;
				
				getBusinessMonitorChartData(instance, business_panel, business_monitor);
			}
		}
		
        Thread.currentThread().join(0);
	}
}

