namespace java com.github.alphafoxz.oneboot.sdk.thrift.struct
struct CommandListResponseStruct {
    1:required i64 id
    2:required bool success
    3:optional string message
    4:required list<string> data
}