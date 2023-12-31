/*
 * @(#)ClientTeamServiceImpl.java
 *
 * Copyright 2021 by esoft systems (tm).
 * All rights reserved.
 */
package com.esoftsystem.sso.service.impl;

import java.util.List;

import com.esoftsystem.sso.model.ResourceDto;
import com.esoftsystem.sso.model.TeamDto;
import com.esoftsystem.sso.service.ClientTeamService;
import com.esoftsystem.sso.service.impl.base.BaseSingleSignOnClientService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Thanh Pham
 * @author Chien Nguyen Tien
 * @since 1.0.0
 */
@Service
public class ClientTeamServiceImpl extends BaseSingleSignOnClientService implements ClientTeamService {

  private static final String LOAD_TEAMS_ENDPOINT = "teams";
  private static final String LOAD_TEAMS_RESOURCES_ENDPOINT = "teams/{teamCode}/resources";
  private static final String PARAM_TEAM_CODE = "{teamCode}";

  @Value("${single.sign.on.service.base.url}")
  private String ssoServiceUrl;

  private final RestTemplate restTemplate;

  /**
   * Bean constructor.
   *
   * @param restTemplate the restTemplate
   */
  public ClientTeamServiceImpl(@Qualifier("singleSignOnClientRestTemplate") RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<TeamDto> loadAllAvailableTeams(String tokenType, String accessToken) {
    HttpEntity<Object> requestEntity = getAuthenticatedRequestEntity(tokenType, accessToken, null);
    ParameterizedTypeReference<List<TeamDto>> listResponseType = getListParameterizedResponseType();
    return restTemplate.exchange(ssoServiceUrl + LOAD_TEAMS_ENDPOINT, HttpMethod.GET, requestEntity, listResponseType).getBody();
  }

  @Override
  public List<ResourceDto> loadResources(String tokenType, String accessToken, String teamCode) {
    HttpEntity<Object> requestEntity = getAuthenticatedRequestEntity(tokenType, accessToken, null);
    ParameterizedTypeReference<List<ResourceDto>> listResponseType = getListParameterizedResponseType();
    return restTemplate.exchange(ssoServiceUrl + LOAD_TEAMS_RESOURCES_ENDPOINT.replace(PARAM_TEAM_CODE, teamCode), HttpMethod.GET, requestEntity,
        listResponseType).getBody();
  }
}
