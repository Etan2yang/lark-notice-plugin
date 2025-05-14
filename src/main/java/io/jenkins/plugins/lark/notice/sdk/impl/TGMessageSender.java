package io.jenkins.plugins.lark.notice.sdk.impl;

import io.jenkins.cli.shaded.org.apache.commons.lang.StringUtils;
import io.jenkins.plugins.lark.notice.model.MessageModel;
import io.jenkins.plugins.lark.notice.model.RobotConfigModel;
import io.jenkins.plugins.lark.notice.sdk.model.SendResult;
import io.jenkins.plugins.lark.notice.sdk.model.ding.DingCardMessage;
import io.jenkins.plugins.lark.notice.sdk.model.ding.DingLinkMessage;
import io.jenkins.plugins.lark.notice.sdk.model.ding.DingMdMessage;
import io.jenkins.plugins.lark.notice.sdk.model.ding.DingTextMessage;
import io.jenkins.plugins.lark.notice.sdk.model.tg.TGMessage;
import io.jenkins.plugins.lark.notice.tools.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DingTask implementation for sending Lark messages.
 *
 * @author xm.z
 */
@Slf4j
@Getter
@AllArgsConstructor
public class TGMessageSender extends AbstractMessageSender {

    /**
     * The robot configuration information.
     */
    private final RobotConfigModel robotConfig;

    protected String[] signHeaders() {
        String[] headers = new String[]{};
        if (StringUtils.isNotBlank(robotConfig.getSign())) {
            long timestamp = System.currentTimeMillis();
            headers = new String[]{
                    "timestamp", String.valueOf(timestamp),
                    "sign", robotConfig.createSign(timestamp)
            };
        }
        return headers;
    }

    /**
     * Sends a text message.
     *
     * @param msg Message details.
     * @return Result of the send operation.
     */
    @Override
    public SendResult sendText(MessageModel msg) {
        TGMessage msg2 = new TGMessage(msg.getText(),robotConfig.getKeys(),"Markdown");
        return sendMessage(JsonUtils.toJson(msg2), signHeaders());
    }

    /**
     * Sends a markdown message.
     *
     * @param msg Message details.
     * @return Result of the send operation.
     */
    @Override
    public SendResult sendMarkdown(MessageModel msg) {
        TGMessage msg2 = new TGMessage(msg.getText(),robotConfig.getKeys(),"Markdown");
        return sendMessage(JsonUtils.toJson(msg2), signHeaders());
    }

    /**
     * Sends a link message.
     *
     * @param msg Message details.
     * @return Failure result.
     */
    @Override
    public SendResult sendLink(MessageModel msg) {
        return SendResult.fail("not supported link");
    }

    /**
     * Sends a card message.
     *
     * @param msg Message details.
     * @return Failure result.
     */
    @Override
    public SendResult sendCard(MessageModel msg) {
        TGMessage msg2 = new TGMessage(msg.getText(),robotConfig.getKeys(),"Markdown");

        return sendMessage(JsonUtils.toJson(msg2), signHeaders());
    }

}
