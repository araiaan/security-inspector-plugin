/*
 * The MIT License
 *
 * Copyright 2016 Ksenia Nenasheva <ks.nenasheva@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jenkinsci.plugins.securityinspector.impl.items;

import hudson.model.Item;
import hudson.model.User;
import java.util.HashSet;
import static org.hamcrest.MatcherAssert.assertThat;
import org.jenkinsci.plugins.securityinspector.util.PermissionReportAssert;
import org.jenkinsci.plugins.securityinspector.util.ReportBuilderTestBase;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
/**
 * Tests of {@link ItemForMultipleUsersReportBuilder}.
 * @author Ksenia Nenasheva <ks.nenasheva@gmail.com>
 */
public class ItemForMultipleUsersReportBuilderTest extends ReportBuilderTestBase<ItemForMultipleUsersReportBuilder> {
    
    public ItemForMultipleUsersReportBuilderTest() {
        super(ItemForMultipleUsersReportBuilder.class);
    }
    
    @Test
    public void shouldReportProject1Properly() throws Exception {
        initializeDefaultMatrixAuthSecurity();
        final ItemForMultipleUsersReportBuilder builder = getBuilder();
        
        final ItemForMultipleUsersReportBuilder.ReportImpl report = new ItemForMultipleUsersReportBuilder.ReportImpl(j.jenkins.getItem("project1"));
        assertNotNull(report);
        assertNotNull(User.getAll());
        HashSet<User> users = new HashSet<>();
        for (User user : User.getAll()) {
            users.add(user);
        }
        report.generateReport(users);
        
        // Check entries
        PermissionReportAssert.assertHasPermissions(report, User.get("admin"), 
                Item.READ, Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.DISCOVER, Item.WORKSPACE);
        
        PermissionReportAssert.assertHasPermissions(report, User.get("user1"), 
                Item.READ, Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.DISCOVER);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user1"), 
                Item.CREATE, Item.DELETE, Item.WORKSPACE);
        
        PermissionReportAssert.assertHasPermissions(report, User.get("user2"), 
                Item.READ, Item.DISCOVER);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user2"), 
                Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.WORKSPACE);  
        
        PermissionReportAssert.assertHasPermissions(report, User.get("user3"), 
                Item.READ, Item.DISCOVER);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user3"), 
                Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.WORKSPACE);
    }
    
    @Test
    public void shouldReportProject2Properly() throws Exception {
        initializeDefaultMatrixAuthSecurity();
        final ItemForMultipleUsersReportBuilder builder = getBuilder();
        
        final ItemForMultipleUsersReportBuilder.ReportImpl report = new ItemForMultipleUsersReportBuilder.ReportImpl(j.jenkins.getItem("project2"));
        assertNotNull(report);
        assertNotNull(User.getAll());
        HashSet<User> users = new HashSet<>();
        for (User user : User.getAll()) {
            users.add(user);
        }
        report.generateReport(users);
        
        // Check entries
        PermissionReportAssert.assertHasPermissions(report, User.get("admin"), 
                Item.READ, Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.DISCOVER, Item.WORKSPACE);
        
        PermissionReportAssert.assertHasPermissions(report, User.get("user1"), 
                Item.READ, Item.DISCOVER);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user1"), 
                Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.WORKSPACE);
        
        PermissionReportAssert.assertHasPermissions(report, User.get("user2"), 
                Item.READ, Item.DELETE, Item.BUILD, Item.CANCEL, Item.DISCOVER);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user2"), 
                Item.CREATE, Item.CONFIGURE, Item.WORKSPACE);
          
        PermissionReportAssert.assertHasPermissions(report, User.get("user3"), 
                Item.READ, Item.DISCOVER);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user3"), 
                Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.WORKSPACE);
    }
    
    @Test
    public void shouldReportFolderProperly() throws Exception {
        initializeDefaultMatrixAuthSecurity();
        final ItemForMultipleUsersReportBuilder builder = getBuilder();
        
        final ItemForMultipleUsersReportBuilder.ReportImpl report = new ItemForMultipleUsersReportBuilder.ReportImpl(j.jenkins.getItem("folder"));
        assertNotNull(report);
        assertNotNull(User.getAll());
        HashSet<User> users = new HashSet<>();
        for (User user : User.getAll()) {
            users.add(user);
        }
        report.generateReport(users);
        
        // Check entries
        PermissionReportAssert.assertHasPermissions(report, User.get("admin"), 
                Item.READ, Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.DISCOVER, Item.WORKSPACE);
        
        PermissionReportAssert.assertHasPermissions(report, User.get("user1"), 
                Item.READ, Item.DISCOVER);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user1"), 
                Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.WORKSPACE);
        
        PermissionReportAssert.assertHasPermissions(report, User.get("user2"), 
                Item.READ, Item.DISCOVER);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user2"), 
                Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.WORKSPACE);
        
        PermissionReportAssert.assertHasPermissions(report, User.get("user3"), 
                Item.READ, Item.DISCOVER);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user3"), 
                Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.WORKSPACE);
    }
    
    @Test
    public void shouldReportProjectInFolderProperly() throws Exception {
        initializeDefaultMatrixAuthSecurity();
        final ItemForMultipleUsersReportBuilder builder = getBuilder();
        
        final ItemForMultipleUsersReportBuilder.ReportImpl report = new ItemForMultipleUsersReportBuilder.ReportImpl(j.jenkins.getItemByFullName("folder/projectInFolder"));
        assertNotNull(report);
        assertNotNull(User.getAll());
        HashSet<User> users = new HashSet<>();
        for (User user : User.getAll()) {
            users.add(user);
        }
        report.generateReport(users);
        
        // Check entries
        PermissionReportAssert.assertHasPermissions(report, User.get("admin"), 
                Item.READ, Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.DISCOVER, Item.WORKSPACE);
        
        PermissionReportAssert.assertHasPermissions(report, User.get("user1"), 
                Item.READ, Item.DISCOVER);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user1"), 
                Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.WORKSPACE);
        
        PermissionReportAssert.assertHasPermissions(report, User.get("user2"), 
                Item.READ, Item.DISCOVER);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user2"), 
                Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, 
                Item.DELETE, Item.WORKSPACE);
        
        PermissionReportAssert.assertHasPermissions(report, User.get("user3"), 
                Item.READ, Item.DISCOVER, Item.CONFIGURE, Item.BUILD, Item.CANCEL, Item.CREATE, Item.DELETE);
        PermissionReportAssert.assertHasNotPermissions(report, User.get("user3"), 
                Item.WORKSPACE);
    }
    
    @Test
    public void shouldDownloadReport4Project1() throws Exception {
        
        initializeDefaultMatrixAuthSecurity();
        final ItemForMultipleUsersReportBuilder builder = getBuilder();
        
        final ItemForMultipleUsersReportBuilder.ReportImpl report = new ItemForMultipleUsersReportBuilder.ReportImpl(j.jenkins.getItem("project1"));
        assertNotNull(report);
        assertNotNull(User.getAll());
        HashSet<User> users = new HashSet<>();
        for (User user : User.getAll()) {
            users.add(user);
        }
        report.generateReport(users);
        
        assertThat("Report target name must be equal to 'project1'", report.getReportTargetName().equals("project1")); 

        String reportInCSV = report.getReportInCSV();
        assertNotNull(reportInCSV);
        
        for (User user : users) {
            assertThat("CSV Report must had row " + user, reportInCSV.contains(user.getDisplayName()));
        }
    }
}
