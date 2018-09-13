'use strict';

import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {requireNativeComponent, View, UIManager, findNodeHandle} from 'react-native';

const RCTRefreshLayout = requireNativeComponent("RCTRefreshLayout", RefreshLayout);

export default class RefreshLayout extends Component {
    static propTypes = {
        refreshable: PropTypes.bool,
        isContentScroll: PropTypes.bool,
        ...View.propTypes
    };

    render() {
        return (
            <RCTRefreshLayout
                ref={(c) => this.refresh = c}  {...this.props}>
                {this.props.children}
            </RCTRefreshLayout>
        )
    }

    getRefreshLayoutHandle = () => {
        return findNodeHandle(this.refresh);
    };

    finishRefresh = () => {
        UIManager.dispatchViewManagerCommand(
            this.getRefreshLayoutHandle(),
            UIManager.RCTRefreshLayout.Commands.finishRefresh,
            null
        );
    };

    startRefresh = () => {
        UIManager.dispatchViewManagerCommand(
            this.getRefreshLayoutHandle(),
            UIManager.RCTRefreshLayout.Commands.startRefresh,
            null
        );
    }
}