package com.johnny.bankworker.service.impl.workerbalance;

import com.johnny.bankworker.common.DataDifference;
import com.johnny.bankworker.common.DateUtils;
import com.johnny.bankworker.common.ObjectConvertUtils;
import com.johnny.bankworker.constant.ClockStatusConstant;
import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.entity.BalanceAnalyse4FinancialClockEntity;
import com.johnny.bankworker.entity.BalanceAnalyse4FinancialEntity;
import com.johnny.bankworker.entity.BalanceAnalyse4LobbyEntity;
import com.johnny.bankworker.entity.BalanceCallbackAnalyseEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.workerbalance.BalanceAnalyseMapper;
import com.johnny.bankworker.vo.BalanceAnalyse4FinancialVO;
import com.johnny.bankworker.vo.BalanceAnalyse4LobbyVO;
import com.johnny.bankworker.vo.BalanceCallbackAnalyseVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceAnalyseImpl {
    @Autowired
    private BalanceAnalyseMapper myMapper;
    private Logger logger = LogManager.getLogger(BalanceAnalyseImpl.class);

    public UnifiedResponse findFinancialBusinessAnalyse(String bankCode, String branchCode, String fromDate, String toDate) {
        try {
            List<BalanceAnalyse4FinancialVO> modelList = new ArrayList<>();
            List<BalanceAnalyse4FinancialEntity> entityList =  myMapper.searchFinancialBusinessAnalyse(bankCode, branchCode, fromDate, toDate);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            for (BalanceAnalyse4FinancialEntity entity : entityList) {
                String currentClockTime = "";
                String afterClockTime = "";
                String currentClockStatus = "";
                DataDifference waitTotalTime = new DataDifference();
                DataDifference busyTotalTime = new DataDifference();
                DataDifference leaveTotalTime = new DataDifference();
                DataDifference offDutyTotalTime = new DataDifference();

                List<BalanceAnalyse4FinancialClockEntity> clockEntityList = myMapper.searchFinancialClockList(bankCode, branchCode, entity.getFinancialID(), fromDate, toDate);
                for(int i = 0; i < clockEntityList.size(); i++){
                    if(i + 1 >= clockEntityList.size()){
                        break;
                    }
                    currentClockTime = clockEntityList.get(i).getCreateTime();
                    currentClockStatus = clockEntityList.get(i).getClockStatus();
                    afterClockTime = clockEntityList.get(i + 1).getCreateTime();

                    switch (currentClockStatus){
                        case ClockStatusConstant.WAITING:
                            getTotalDataDifference(waitTotalTime, currentClockTime, afterClockTime);
                            break;
                        case ClockStatusConstant.BUSY:
                            getTotalDataDifference(busyTotalTime, currentClockTime, afterClockTime);
                            break;
                        case ClockStatusConstant.LEAVE:
                            getTotalDataDifference(leaveTotalTime, currentClockTime, afterClockTime);
                            break;
                        case ClockStatusConstant.OFF_DUTY:
                            getTotalDataDifference(offDutyTotalTime, currentClockTime, afterClockTime);
                            break;
                        default:
                            break;
                    }
                }
                modelList.add(buildBalanceAnalyse4FinancialVO(entity, waitTotalTime, busyTotalTime, leaveTotalTime, offDutyTotalTime));
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    public UnifiedResponse findCallbackBusinessAnalyse4Financial(String bankCode, String branchCode, int financialID, String fromDate, String toDate) {
        try {
            List<BalanceCallbackAnalyseVO> modelList = new ArrayList<>();
            List<BalanceCallbackAnalyseEntity> entityList =  myMapper.searchCallbackBusinessAnalyse4Financial(bankCode, branchCode, financialID, fromDate, toDate);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (BalanceCallbackAnalyseEntity entity : entityList) {
                BalanceCallbackAnalyseVO model = new BalanceCallbackAnalyseVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    public UnifiedResponse findLobbyBusinessAnalyse(String bankCode, String branchCode, String fromDate, String toDate) {
        try {
            List<BalanceAnalyse4LobbyVO> modelList = new ArrayList<>();
            List<BalanceAnalyse4LobbyEntity> entityList =  myMapper.searchLobbyBusinessAnalyse(bankCode, branchCode, fromDate, toDate);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (BalanceAnalyse4LobbyEntity entity : entityList) {
                BalanceAnalyse4LobbyVO model = new BalanceAnalyse4LobbyVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    public UnifiedResponse findCallbackBusinessAnalyse4Lobby(String bankCode, String branchCode, int lobbyID, String fromDate, String toDate) {
        try {
            List<BalanceCallbackAnalyseVO> modelList = new ArrayList<>();
            List<BalanceCallbackAnalyseEntity> entityList =  myMapper.searchCallbackBusinessAnalyse4Lobby(bankCode, branchCode, lobbyID, fromDate, toDate);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (BalanceCallbackAnalyseEntity entity : entityList) {
                BalanceCallbackAnalyseVO model = new BalanceCallbackAnalyseVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    private BalanceAnalyse4FinancialVO buildBalanceAnalyse4FinancialVO(
            BalanceAnalyse4FinancialEntity entity,
            DataDifference waitTotalTime,
            DataDifference busyTotalTime,
            DataDifference leaveTotalTime,
            DataDifference offDutyTotalTime){
        BalanceAnalyse4FinancialVO model = new BalanceAnalyse4FinancialVO();
        model.setFinancialID(entity.getFinancialID());
        model.setFinancialName(entity.getFinancialName());
        model.setProcessedBusinessCount(entity.getProcessedBusinessCount());
        model.setWaitTotalTime(formatTotalTime(waitTotalTime));
        model.setBusyTotalTime(formatTotalTime(busyTotalTime));
        model.setLeaveTotalTime(formatTotalTime(leaveTotalTime));
        model.setOffDutyTotalTime(formatTotalTime(offDutyTotalTime));
        return model;
    }

    private void getTotalDataDifference(DataDifference totalDataDifference, String currentClockTime, String afterClockTime) throws ParseException {
        DataDifference dataDifference = DateUtils.difference(currentClockTime, afterClockTime);
        totalDataDifference.setDay(totalDataDifference.getDay() + dataDifference.getDay());
        totalDataDifference.setHour(totalDataDifference.getHour() + dataDifference.getHour());
        totalDataDifference.setMin(totalDataDifference.getMin() + dataDifference.getMin());
        totalDataDifference.setSeconds(totalDataDifference.getSeconds() + dataDifference.getSeconds());
    }

    private String formatTotalTime(DataDifference totalTime){
        String totalDay = "";
        String totalHour = "";
        String totalMin = "";
        String totalSeconds = "";
        if(totalTime.getDay() > 0){
            totalDay = totalTime.getDay() + "天";
        }
        if(totalTime.getHour() > 0){
            totalHour = totalTime.getHour() + "小时";
        }
        if(totalTime.getMin() > 0){
            totalMin = totalTime.getMin() + "分";
        }
        if(totalTime.getSeconds() > 0){
            totalSeconds = totalTime.getSeconds() + "秒";
        }
        return totalDay + totalHour + totalMin + totalSeconds;
    }
}
