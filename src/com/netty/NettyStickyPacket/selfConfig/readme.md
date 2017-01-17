在本例中采取了固定长度的方式解决粘包拆包问题，有以下几个显著的变化：
[1] 在Client发送时，设置了一个4字节的长度头，该长度头记录了内容的长度。
[2] Server端设置了MyDecoder继承自FrameDecoder。注释非常清晰。
[3] 在MyDecoder中对字节数组做了处理，包装成了String类型。所以下一个handler直接处理String类型即可。