package cn.whu.wy.query;

import cn.whu.wy.query.processor.Query;
import cn.whu.wy.query.request.BaseMsg;
import cn.whu.wy.query.request.QueryById;
import cn.whu.wy.query.request.QueryByName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Author WangYong
 * Date 2022/05/22
 * Time 10:45
 */
@Slf4j
@Service
public class QueryService implements CommandLineRunner {

    @Autowired
    Map<String, Query> queryMap;


    @Override
    public void run(String... args) throws Exception {
        log.info(queryMap.toString());

        QueryById msg1 = QueryById.builder().seq("202205220001").id(1).build();
        QueryByName msg2 = QueryByName.builder().seq("202205220002").name("Han").build();

        process(msg1);
        process(msg2);

    }

    private void process(BaseMsg msg) throws Exception {
        Object ret = getProcessor(msg).query(msg);
        log.info(ret.toString());
    }

    private Query getProcessor(BaseMsg msg) throws Exception {
        if (msg instanceof QueryById) {
            return queryMap.get("idQuery");
        } else if (msg instanceof QueryByName) {
            return queryMap.get("nameQuery");
        } else {
            throw new Exception("no processor found for this kind message");
        }
    }
}
