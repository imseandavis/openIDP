/***********************************************************************************************
*
* Copyright 2018 Infosys Ltd.
* Use of this source code is governed by MIT license that can be found in the LICENSE file or at 
* https://opensource.org/licenses/MIT.
*
***********************************************************************************************/
package org.infy.idp.businessapi;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;

import org.infy.entities.artifact.Artifact;
import org.infy.entities.artifact.ArtifactList;
import org.infy.idp.dataapi.base.PostGreSqlDbContext;
import org.infy.idp.dataapi.services.DeploymentDL;
import org.infy.idp.dataapi.services.JobDetailsDL;
import org.infy.idp.utils.ConfigurationManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jtest.AppContext;

/**
 * EnvironmentBLTest is a test class for EnvironmentBL
 *
 * @see org.infy.idp.businessapi.EnvironmentBL
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContext.class)
public class EnvironmentBLTest {

	@Mock
	private JobDetailsDL getJobDetails;

	@Mock
	private DeploymentDL deploymentDL;

	@InjectMocks
	private JobsBL jobsBL;

	@InjectMocks
	private EnvironmentBL environmentBL;

	@Spy
	@InjectMocks
	private PostGreSqlDbContext postGreSqlDbContext;

	@Spy
	@Autowired
	private ConfigurationManager configurationManager;

	public EnvironmentBLTest() {
		/*
		 * This constructor should not be modified. Any initialization code should be
		 * placed in the setUp() method instead.
		 */

	}

	/**
	 * Used to set up the test. This method is called by JUnit before each of the
	 * tests are executed.
	 * 
	 */

	@Test
	public void testGetArtifactList() throws Throwable {
		ArtifactList artifactList = new ArtifactList();
		artifactList.setApplicationName("JFrogTest");
		artifactList.setPipelineName("JFrogTest2");
		artifactList.setEnvironmentName("dev");
		artifactList.setReleaseNumber("1.0.0");
		ArtifactList list = environmentBL.getArtifactList(artifactList);
		assertNotNull(list);
	}

	@Test
	public void testUpdateSlave() throws Throwable {
		environmentBL.updateSlave("WinSlave");
	}

	@Test
	public void getLatestArtifactDetails() throws Throwable {
		Artifact artifact = environmentBL.getLatestArtifactDetails("JFrogTest_JFrogTest2");
		assertNotNull(artifact);
	}

	@Before
	public void setUp() throws Exception {
		/*
		 * Add any necessary initialization code here (e.g., open a socket). Call
		 * Repository.putTemporary() to provide initialized instances of objects to be
		 * used when testing.
		 */

		try {

			MockitoAnnotations.initMocks(this);
			Method postConstruct = PostGreSqlDbContext.class.getDeclaredMethod("init", null);
			postConstruct.setAccessible(true);
			postConstruct.invoke(postGreSqlDbContext);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Utility main method. Runs the test cases defined in this test class.
	 * 
	 * Usage: java EnvironmentBLTest
	 * 
	 * @param args command line arguments are not needed
	 */
	public static void main(String[] args) {

		org.junit.runner.JUnitCore.main("org.infy.idp.businessapi.EnvironmentBLTest");
	}

	/**
	 * Get the class object of the class which will be tested.
	 * 
	 * @return the class which will be tested
	 */
	public Class getTestedClass() {
		return EnvironmentBL.class;
	}
}
