package com.tctav.momos.db;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.tctav.momos.model.User;
import com.tctav.momos.model.meta.ChoiceType;
import com.tctav.momos.model.meta.EntryType;
import com.tctav.momos.model.meta.FormType;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Validate;
import org.wisdom.orientdb.conf.WOrientConf;
import org.wisdom.orientdb.document.OrientDbDocumentCommand;
import org.wisdom.orientdb.object.OrientDbRepoCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * created: 3/24/15.
 *
 * @author <a href="mailto:jbardin@tech-arts.com">Jonathan M. Bardin</a>
 */
@Component
@Provides(specifications = {OrientDbRepoCommand.class,OrientDbDocumentCommand.class})
@Instantiate
public class DbManager implements OrientDbRepoCommand,OrientDbDocumentCommand{
    private WOrientConf conf;

    private List<Class<?>> entities = new ArrayList<>(3);

    @Validate
    public void start(){
        entities.add(User.class);
        entities.add(FormType.class);

        conf = new WOrientConf("momos","memory:momos","momo","momo", Collections.EMPTY_LIST);
    }

    @Override
    public WOrientConf getConf() {
        return conf;
    }

    @Override
    public List<Class<?>> getEntityClass() {
        return unmodifiableList(entities);
    }

    @Override
    public void init(OObjectDatabaseTx db) {
        db.getEntityManager().registerEntityClass(User.class);
        db.getEntityManager().registerEntityClass(ChoiceType.class);
        db.getEntityManager().registerEntityClass(EntryType.class);
        db.getEntityManager().registerEntityClass(FormType.class);

        //populate
        if(!db.browseClass(User.class.getSimpleName()).hasNext()) {
            User john = new User();
            john.setEmail("john@free.fr");
            john.setPassword("pass");
            john.setName("John");
            db.save(john);
        }
    }

    @Override
    public void destroy(OObjectDatabaseTx db) {
        db.getEntityManager().deregisterEntityClass(User.class);
        db.getEntityManager().deregisterEntityClass(ChoiceType.class);
        db.getEntityManager().deregisterEntityClass(EntryType.class);
        db.getEntityManager().deregisterEntityClass(FormType.class);
    }


    //ODocument
    @Override
    public void init(ODatabaseDocumentTx db) {

    }

    @Override
    public void destroy(ODatabaseDocumentTx db) {

    }
}
