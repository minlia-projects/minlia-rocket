package com.minlia.rocket.security.rebecca.body;

import com.minlia.rocket.data.body.AbstractQueryRequestBody;
import com.minlia.rocket.enumeration.Gender;
import com.minlia.rocket.enumeration.Status;
import lombok.Data;

@Data
public class UserQueryRequestBody extends AbstractQueryRequestBody {

  private String username;
  private String mobile;
  private String email;

  private Gender gender;
  private Integer type;
  private Status status;


  private String startDate;

  private String endDate;

}
