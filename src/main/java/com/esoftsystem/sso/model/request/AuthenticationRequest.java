/*
 * @(#)AuthenticationRequest.java
 *
 * Copyright 2021 by esoft systems (tm).
 * All rights reserved.
 */
package com.esoftsystem.sso.model.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vinh Nguyen
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {

  private static final long serialVersionUID = -1184506861427539574L;
  @JsonProperty(value = "username", required = true)
  private String username;
  @JsonProperty(value = "password", required = true)
  private String password;

  @JsonProperty("userIpAddress")
  private String userIpAddress;
  @JsonProperty("agentInformation")
  private String agentInformation;
}
