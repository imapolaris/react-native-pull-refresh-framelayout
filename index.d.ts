import * as React from 'react';

declare module "react-native-pull-refresh-framelayout" {
    /**
     * 基础类
     */
    export class Pullable extends React.Component<any, any>{}

    /**
     * 可下拉刷新的View
     */
    export class PullView extends React.Component<any, any>{}

    /**
     * 可下拉刷新的ScrollView
     */
    export class PullScrollView extends React.Component<any, any>{}

    /**
     * 可下拉刷新的ListView
     */
    export class PullListView extends React.Component<any, any>{}

    /**
     * 可下拉刷新的FlatList
     */
    export class PullFlatList extends React.Component<any, any>{}
}