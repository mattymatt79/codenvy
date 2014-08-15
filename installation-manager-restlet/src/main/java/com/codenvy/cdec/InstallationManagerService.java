/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 *  [2012] - [2014] Codenvy, S.A.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.cdec;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Dmytro Nochevnov
 * TODO check paths
 */
@Path("im")
public interface InstallationManagerService {

    /**
     * Perform request to get unique and transient information from server to build the authentication credentials for the next requests.
     */
    @HEAD
    @Path("empty")
    @Produces(MediaType.TEXT_HTML)
    public void obtainChallengeRequest();

    /**
     * Scans all available artifacts and returns their current versions.
     */
    @GET
    @Path("get-available-2-download-artifacts")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonRepresentation doGetAvailable2DownloadArtifacts();

    /**
     * Downloads updates.
     */
    @GET
    @Path("download-updates")
    @Produces(MediaType.TEXT_HTML)
    public JsonRepresentation doDownloadUpdates();

    /**
     * @return the list of artifacts with newer versions than currently installed
     */
    @GET
    @Path("get-new-versions")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonRepresentation doGetNewVersions();

    /**
     * Checks if new versions are available. The retrieved list can be obtained by invoking {@link #doCheckNewVersions(String)} ()} method.
     */
    @GET
    @Path("check-new-versions/{version}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonRepresentation doCheckNewVersions(@PathParam(value = "version") final String version) throws JSONException;
}
