<view><view class="line"><view class="left" style="{{'background-color:'+(color)+';'}}"></view><view class="right">{{title}}</view></view><block wx:for="{{info}}" wx:for-item="item" wx:for-index="index" wx:key="index"><view><view class="card"><text>{{item}}</text></view></view></block></view>