/**
 * Autogenerated by Thrift Compiler (0.18.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.github.alphafoxz.oneboot.sdk.gen.thrift.enums;


@javax.annotation.processing.Generated(value = "Autogenerated by Thrift Compiler (0.18.1)", date = "2023-09-06")
public enum SdkFileTypeEnum implements org.apache.thrift.TEnum {
  LOCAL_FILE(0),
  LOCAL_DIR(1);

  private final int value;

  private SdkFileTypeEnum(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  @Override
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static SdkFileTypeEnum findByValue(int value) { 
    switch (value) {
      case 0:
        return LOCAL_FILE;
      case 1:
        return LOCAL_DIR;
      default:
        return null;
    }
  }
}