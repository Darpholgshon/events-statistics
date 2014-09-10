package com.pressassociation.test;

import com.pressassociation.events.db.ConnectionIT;
import com.pressassociation.events.db.QueryRepositoryIT;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * ****************************************************************************************
 *
 * @author <a href="ralph.hodgson@pressassociation.com">Ralph Hodgson</a>
 * @since 03/09/2014 14:32
 * <p/>
 * ****************************************************************************************
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( {ConnectionIT.class, QueryRepositoryIT.class} )
public class TestSuite {
}
