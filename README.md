# micro-seed
1.本工程作为工作业余学习提高，扩展知识，内容会杂乱，力求做到 有章。

2.框架选型:spring boot 使用2.0版本。
    springboot
    springcloud
    consul
    
3.vert.x

    https://vertxchina.github.io/vertx-translation-chinese/start/FAQ.html
    
    1.vert.x建立了一个内部的线程安全机制，排除多线程并发的干扰。
    
    2.vert.x保证同一个普通的verticle(也就是event loop verticle,非worker verticle)内部的所有处理器(handler)
        都只会有同一个eventloop线程调用，由此保证verticle内部的线程安全。
        
    3.
    
