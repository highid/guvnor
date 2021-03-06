/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.guvnor.server.repository;

import org.drools.guvnor.client.common.AssetFormats;
import org.drools.guvnor.server.GuvnorTestBase;
import org.drools.guvnor.server.ServiceImplementation;
import org.drools.ide.common.client.modeldriven.brl.RuleModel;
import org.drools.ide.common.client.modeldriven.dt.TypeSafeGuidedDecisionTable;
import org.drools.ide.common.server.util.BRXMLPersistence;
import org.drools.ide.common.server.util.GuidedDTXMLPersistence;
import org.drools.repository.AssetItem;
import org.drools.repository.PackageItem;
import org.junit.Test;

public class EventsTest extends GuvnorTestBase {

    @Test
    public void testLoadSave() throws Exception {
        System.setProperty( "guvnor.saveEventListener",
                            "org.drools.guvnor.server.repository.SampleSaveEvent" );

        ServiceImplementation impl = getServiceImplementation();

        PackageItem pkg = impl.getRulesRepository().createPackage( "testLoadSaveEvents",
                                                                   "" );
        AssetItem asset = pkg.addAsset( "testLoadSaveEvent",
                                        "" );
        asset.updateFormat( AssetFormats.BUSINESS_RULE );

        RuleModel m = new RuleModel();
        m.name = "mrhoden";

        asset.updateContent( BRXMLPersistence.getInstance().marshal( m ) );
        asset.checkin( "" );

        asset = pkg.addAsset( "testLoadSaveEventDT",
                              "" );
        asset.updateFormat( AssetFormats.DECISION_TABLE_GUIDED );
        TypeSafeGuidedDecisionTable gt = new TypeSafeGuidedDecisionTable();
        asset.updateContent( GuidedDTXMLPersistence.getInstance().marshal( gt ) );
        asset.checkin( "" );
    }

}
