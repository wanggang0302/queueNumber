package com.jfsoft.queue.core;

import com.jfsoft.model.CallingDevice;
import com.jfsoft.queue.entity.PerCheckinfo;
import com.jfsoft.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 代表一个服务队列
 * wanggang
 * 2017年8月28日
 */
public class QueueCenter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //医生最大数量（需要从数据库读取）
    private final static int MAXCOUNT = 10;

    //呼号器队列（即体检医生的数量）
    private BlockingQueue<CallingDevice> callingDevices;
    //体检者队列
    private BlockingQueue<PerCheckinfo> perCheckinfos;
    //体检者VIP队列
    private BlockingQueue<PerCheckinfo> vipQueue;

    public BlockingQueue<CallingDevice> getCallingDevices() {
        return callingDevices;
    }

    public List<PerCheckinfo> getPerCheckinfoList() {
        List<PerCheckinfo> perCheckinfoList = null;
        if(null!=perCheckinfos) {
            perCheckinfoList = new ArrayList<PerCheckinfo>();
            for(Iterator<PerCheckinfo> it = perCheckinfos.iterator(); it.hasNext();) {
                perCheckinfoList.add(it.next());
            }
        }
        return perCheckinfoList;
    }

    public List<PerCheckinfo> getVipPerCheckinfoList() {
        List<PerCheckinfo> perCheckinfoList = null;
        if(null!=vipQueue) {
            perCheckinfoList = new ArrayList<PerCheckinfo>();
            for(Iterator<PerCheckinfo> it = vipQueue.iterator(); it.hasNext();) {
                perCheckinfoList.add(it.next());
            }
        }
        return perCheckinfoList;
    }

    public QueueCenter() {

        //创建一个科室下的医生队列
        this.callingDevices = new LinkedBlockingQueue<CallingDevice>();

        //医生工作就绪,创建体检者队列
        this.perCheckinfos = new LinkedBlockingQueue<PerCheckinfo>();
        //创建VIP队列
        this.vipQueue = new LinkedBlockingQueue<PerCheckinfo>();

    }

    /**
     * 体检者排队
     * @param perCheckinfo
     */
    public void produce(PerCheckinfo perCheckinfo) {

        this.perCheckinfos.add(perCheckinfo);
    }

    /**
     * 将体检者移出队列
     */
    public boolean remove(PerCheckinfo perCheckinfo) {

        //获得体检号，根据体检号匹配队列
        String testno = perCheckinfo.getTestno();

        for(Iterator<PerCheckinfo> it = perCheckinfos.iterator(); it.hasNext();) {
            PerCheckinfo detectedOne = it.next();
            if(null!=detectedOne && !StringUtils.isBlank(detectedOne.getTestno())
                    && detectedOne.getTestno().equals(testno)) {
                this.perCheckinfos.remove(detectedOne);
                return true;
            }
        }
        return false;
    }

    /**
     * 更新队列
     */
    public boolean update(List<PerCheckinfo> perCheckinfoList) {

        try {

            this.perCheckinfos.clear();
            this.perCheckinfos.addAll(perCheckinfoList);

            return true;
        } catch (Exception e) {
            logger.error("update queue failure! err msg is {}.", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 消费，并从队列中移除
     */
    public PerCheckinfo custom() throws Exception {

        PerCheckinfo wentToPerCheckinfo = this.perCheckinfos.take();
        logger.debug("doctor went to check {}.", wentToPerCheckinfo);

        return wentToPerCheckinfo;
    }

    /**
     * 消费，不从队列中移除
     */
    public PerCheckinfo peek() throws Exception {

        PerCheckinfo wentToPerCheckinfo = this.perCheckinfos.peek();
        wentToPerCheckinfo.setState(Constants.CHECK_STATUS_ING);
        logger.debug("doctor went to check {}.", wentToPerCheckinfo);

        return wentToPerCheckinfo;
    }

}
