package com.wetoband.template.constant;

public enum WordType {
    DynamicWord(0), // 动态词汇
    TermWord(1), // 术语词汇
    RoleWord(2), // 角色词汇
    UserWord(3), // 用户词汇
    ChatroomWord(4), // 消息板词汇
    OrganizationCustomWord(5), // 机构自定义词汇
    PositionWord(6), // 职位词汇
    DepartmentWord(7), // 部门词汇
    ToolWord(8), // 工具词汇
    UnitWord(9), // 部件词汇
    PublicWord(10), // 公共词汇
    OrganizationInfoWord(11), // 机构信息词汇
    BandWord(12), // 帮区词汇
    ;
    private final int value;
    WordType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
